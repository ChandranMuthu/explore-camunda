package com.chandran.workflow.delegates;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageApiService {
	
	@Value("${startTaskMessageEvent}")
	String startTaskMessageEvent;

    private final RuntimeService runtimeService;

    public MessageApiService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    //@Async
  //  public void triggerMessageEvent(final String businessKey, TaskMessage taskmessage) {
    	
 		    public void triggerMessageEvent(final String businessKey, final Map<String, Object> variables) {
         //runtimeService.createSignalEvent("signal_callRestApi").setVariables(variables).send();
   //      runtimeService.correlateMessage(startTaskMessageEvent, businessKey ,taskmessage);

     	//processvariables.put("startTaskMessageEvent",businessKey);
     		runtimeService.correlateMessage("startTaskMessageEvent",businessKey,variables);
    }
}

