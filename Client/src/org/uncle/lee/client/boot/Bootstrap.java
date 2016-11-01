package org.uncle.lee.client.boot;

import org.uncle.lee.handler.client.ClickHandler;
import org.uncle.lee.handler.client.InitEngineHandler;
import org.uncle.lee.handler.engine.OnInitDoneHandler;
import org.uncle.lee.ipc.IpcFactory;
import org.uncle.lee.ipc.pipeline.ClientPipeline;
import org.uncle.lee.ipc.pipeline.Pipeline;
import org.uncle.lee.ipc.pipeline.PipelineManager;

public class Bootstrap {
	private PipelineManager pipelineManager;
	
	public void init(){
		pipelineManager = IpcFactory.getInstance().createPipeLineManager();
		
		initClientPipeline(pipelineManager.clientPipeline());
		initEnginePipeline(pipelineManager.enginePipeline());
	}

	public PipelineManager pipelineManager(){
		return pipelineManager;
	}
	
	private void initEnginePipeline(Pipeline enginePipeline) {
		enginePipeline.add(new OnInitDoneHandler());
	}

	private void initClientPipeline(ClientPipeline clientPipeline) {
		clientPipeline.add(new InitEngineHandler());
		clientPipeline.add(new ClickHandler());
	}
}
