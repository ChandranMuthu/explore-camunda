package com.chandran.workflow.delegates;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ApiHelper {

    private final RuntimeService runtimeService;

    public ApiHelper(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    //@Async
    public void signalApiCall(final String businessKey, final Map<String, Object> variables) {
//        runtimeService.createSignalEvent("signal_callRestApi").setVariables(variables).send();
        runtimeService.correlateMessage("messageEvent", businessKey, variables);
    }
}
