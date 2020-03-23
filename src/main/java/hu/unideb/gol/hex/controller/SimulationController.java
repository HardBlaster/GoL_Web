package hu.unideb.gol.hex.controller;

import hu.unideb.gol.hex.controller.model.InitData;
import hu.unideb.gol.hex.gol.model.Environment;
import hu.unideb.gol.hex.gol.model.RuleSystem;
import hu.unideb.gol.hex.gol.model.Utils;
import hu.unideb.gol.hex.shared.CustomLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/simulator")
public class SimulationController {

    CustomLog log;

    Environment environment;
    RuleSystem ruleSystem;
    int AR0;

    @RequestMapping(value = "/initData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InitData> postInitData(@RequestBody InitData initData) {
        log.log("\n--------------------postInitData--------------------");

        AR0 = initData.getAliveRatio();
        log.log("AR0= " + AR0);

        environment = new Environment(initData.getWidth(), initData.getHeight());
        log.log("Environment defined");

        ruleSystem = new RuleSystem(initData.getBorn(), initData.getStable(), initData.getDead());
        log.log("Rulesystem defined");

        environment.setRuleSystem(ruleSystem);
        log.log("Rulesystem set");

        log.log("--------------------postInitData--------------------\n");
        return new ResponseEntity<>(initData, HttpStatus.OK);
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public ResponseEntity<int[][]> generateEnvironment() {
        log.log("\n--------------------generateEnvironment--------------------");

        if (environment != null
                && ruleSystem != null) {
            log.log("TRUE");

            environment.populate(AR0);
            log.log("Environment populated");
            log.log(Arrays.deepToString(Utils.simplifyEnvironment(environment)).replace(", [", ",\n ["));
            log.log("\n--------------------generateEnvironment--------------------");

            return new ResponseEntity<>(Utils.simplifyEnvironment(environment), HttpStatus.OK);

        }

        log.log("\n--------------------generateEnvironment--------------------");
        log.log("FALSE");
        return new ResponseEntity<>(new int[][]{}, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @RequestMapping(value = "/getNextGen", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<int[][]> getNextGen() {
        log.log("\n--------------------getNextGen--------------------");

        if (environment != null
                && ruleSystem != null) {
            log.log("TRUE");

            int[][] neighbors = environment.nextGen();
            log.log("--------------------NEIGHBORS--------------------");
            log.log(Arrays.deepToString(neighbors).replace(", [", ",\n ["));
            log.log("--------------------NEIGHBORS--------------------");

            log.log("Environment evolved");
            log.log(Arrays.deepToString(Utils.simplifyEnvironment(environment)).replace(", [", ",\n ["));
            log.log("\n--------------------getNextGen--------------------");

            return new ResponseEntity<>(Utils.simplifyEnvironment(environment), HttpStatus.OK);

        }

        log.log("\n--------------------getNextGen--------------------");
        log.log("FALSE");
        return new ResponseEntity<>(new int[][]{}, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PostConstruct
    public void init() {
        log = new CustomLog();
        log.setURL("F:\\Web_FoL_Hex\\LOG\\SimulatorController\\");
    }

    @PreDestroy
    public void destroy() {
        log = null;
    }
}
