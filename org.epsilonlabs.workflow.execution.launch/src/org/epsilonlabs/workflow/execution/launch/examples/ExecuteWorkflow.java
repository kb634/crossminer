/*******************************************************************************
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Konstantinos Barmpis - initial API and implementation
 ******************************************************************************/
package org.epsilonlabs.workflow.execution.launch.examples;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.epsilonlabs.workflow.execution.subscription.WorkflowGraphicalChangeSubscription;

import workflow.Task;

/**
 * @generated NOT
 * @author kb
 *
 */
public class ExecuteWorkflow extends Job {

	private Resource resource = null;

	public ExecuteWorkflow(Resource r) {
		super("workflow execution job");
		resource = r;
	}

	public IStatus run(IProgressMonitor m) {

		// try find out workload based on ???
		SubMonitor subMonitor = SubMonitor.convert(m, 6);

		// m.beginTask("Workflow execution task", 3);

		try {
			execute(subMonitor);
		} catch (Exception e) {
			e.printStackTrace();
			m.done();
			return new Status(IStatus.ERROR, "org.epsilonlabs.workflow.execution", "execution failed");
		}
		m.done();
		return new Status(IStatus.OK, "org.epsilonlabs.workflow.execution", "execution successful");
	}

	public void execute(IProgressMonitor m) throws Exception {

		long init = System.currentTimeMillis();

		// IWorkbenchWindow window =
		// HandlerUtil.getActiveWorkbenchWindowChecked(null);

		System.out.println("Running execution...");

		// FIXME execution stub
		WorkflowGraphicalChangeSubscription.getinstance().executionStarted();

		TreeIterator<EObject> children = resource.getAllContents();
		List<Task> sources = new LinkedList<>();

		while (children.hasNext()) {

			EObject child = children.next();

			if (child instanceof Task) {
				if (((Task) child).getIncoming().isEmpty())
					sources.add((Task) child);
			}

		}

		//

		for (Task d : sources) {

			// System.out.println(c)
			System.out.println(
					"(stub) retrieving data from datasource: " + d.eClass().getName() + "  ofType: " + d.getClass());

			// setInProgressState(child);
			System.out.println("data retrieval in progress");
			WorkflowGraphicalChangeSubscription.getinstance().elementInProgress(d);
			Thread.sleep(500);
			m.worked(1);

		}

		for (Task d : sources) {

			// setBlockedState(child);
			System.err.println("data retrieval blocked!");
			WorkflowGraphicalChangeSubscription.getinstance().elementBlocked(d);
			Thread.sleep(500);
			m.worked(1);

		}

		for (Task d : sources) {

			// setComletedState(child);
			System.out.println("data retrieval complete");
			WorkflowGraphicalChangeSubscription.getinstance().elementComplete(d);
			Thread.sleep(500);
			m.worked(1);
		}

		WorkflowGraphicalChangeSubscription.getinstance().executionEnded();

		// if (wde != null && false) {
		//
		// String fp =
		// wde.getDiagram().eResource().getURI().path().replace("/resource/",
		// "");
		// IFile file = (IFile)
		// ResourcesPlugin.getWorkspace().getRoot().findMember(fp);
		//

		// wde.close(false);

		// if (file != null)
		// page.openEditor(new FileEditorInput(file),
		// "workflow.diagram.part.WorkflowDiagramEditorID");
		// else
		// System.out.println("file not found to re-open editor:
		// " + fp);

		//

		// System.out.println("updating editor: " + editor);

		// WorkflowDiagramEditor wde = (WorkflowDiagramEditor)
		// r.getEditor(false);

		// wde.close(false);

		// wde.showBusy(true);

		// AbstractEMFOperation emfOp = new
		// AbstractEMFOperation(wde.getEditingDomain(), "Recolor
		// Datasources") {
		//
		// @Override
		// protected IStatus doExecute(IProgressMonitor monitor,
		// IAdaptable
		// info) throws ExecutionException {
		//
		// wde.selectionChanged(wde, null);
		//
		// return null;
		// }
		// };
		//
		// try {
		// OperationHistoryFactory.getOperationHistory().execute(emfOp,
		// null, null);
		// } catch (
		//
		// ExecutionException e) {
		// e.printStackTrace();
		// }

		//
		// Iterator<EObject> itt = wde.getDiagram().eAllContents();
		// while (itt.hasNext()) {
		// EObject e = itt.next();
		// List<?> editPolicies =
		// CanonicalEditPolicy.getRegisteredEditPolicies(e);
		// for (Iterator<?> it = editPolicies.iterator(); it.hasNext();) {
		// CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it.next();
		// nextEditPolicy.refresh();
		// }
		// }
		//
		// wde.commandStackChanged(null);
		// wde.close(false);

		// wde.showBusy(false);

		// }

		long totaltime = System.currentTimeMillis() - init;

		System.out.println("Executed workflow, took: " + totaltime / 1000 + "s " + totaltime % 1000 + "ms");

	}

}

/**
 * 
 * // System.err.println("..."); IAction a = new RunAction(page);
 * 
 * a.setId("thisisanidipromiseyou"); a.setEnabled(true);
 * a.setImageDescriptor(ExtendedImageRegistry.getInstance().getImageDescriptor(
 * AbstractUIPlugin.imageDescriptorFromPlugin(
 * "org.epsilonlabs.workflow.diagram", "icons/Go-small.png")));
 * a.setText("Run!"); a.setDescription("Execute the workflow.");
 * 
 * addAction(a); bars.getToolBarManager().add(a);
 * 
 **/
