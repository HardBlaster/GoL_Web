package hu.unideb.gol.hex.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hu.unideb.gol.hex.controller.model.InitData;
import hu.unideb.gol.hex.gol.model.Environment;
import hu.unideb.gol.hex.gol.model.RuleSystem;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/simulator")
public class SimulationController {
    Gson gson;

    Environment environment;
    RuleSystem ruleSystem;

    @RequestMapping(value = "/initData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postInitData(@RequestBody InitData initData) {

        environment = new Environment(initData.getWidth(), initData.getHeight(), initData.getAliveRatio());
        ruleSystem = new RuleSystem(initData.getBorn(), initData.getStable(), initData.getDead());
        environment.setRuleSystem(ruleSystem);

    }

    @RequestMapping(value = "/getNextGen", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getNextGen() {
        if(environment != null
        && ruleSystem != null) {

            environment.nextGen();
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(environment.getMatrix()));

        }

        return ResponseEntity.status(HttpStatus.OK).body("Environment and/or rule-system is null.");
    }

    @PostConstruct
    public void init() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
}
