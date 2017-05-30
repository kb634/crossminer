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
package org.epsilonlabs.workflow.execution.impl;

import org.epsilonlabs.workflow.execution.EventualDataProvider;

import io.reactivex.subjects.PublishSubject;

/**
 * Coordinator of a @GithubEventualDataset observable, providing the appropriate
 * one on request and emiting relevant data when it receives it from Github. A
 * new @GithubExecutor should be created for each call as it only manages
 * one @GithubEventualDataset at a time.
 * 
 * @author kb
 *
 */
public class GithubExecutor implements EventualDataProvider {

	enum FILTERS {
		FILETBYFILEEXTENSION, FILTERBYNAME
	}

	enum DATATYPES {
		REPOSITORIES, FILES, AUTHORS
	}

	protected PublishSubject<Object> ds;

	public GithubExecutor() {

		// ...

	}

	public PublishSubject<Object> getRepositoriesByFileExtension(Iterable<String> exts) {
		// TODO dataset likely specific to return type (in this case dataset of
		// repos?)
		return ds = PublishSubject.create();
	}

	public PublishSubject<Object> getFilesWithFileExtension(String repo, Iterable<String> exts) {
		// TODO dataset likely specific to return type (in this case dataset of
		// files?)
		return ds = PublishSubject.create();
	}

	public PublishSubject<Object> getAuthors(String file) {
		// TODO dataset likely specific to return type (in this case dataset of
		// authors?)
		return ds = PublishSubject.create();
	}

	//
	public void stubRetrieveRepositoriesByFileExtension(String ext) {
		System.out.println("(StubResilientGithubWrapper) providing repository containing " + ext + " files...");

		ds.onNext("repoOf:" + ext);
	}

	//
	public void stubRetrieveAuthorFromFile(String file) {
		System.out.println("(StubResilientGithubWrapper) providing author of " + file + " files...");

		ds.onNext("authorOf:" + file);

	}

	//
	public void stubRetrieveFilesInRepo(String repo) {
		System.out.println("(StubResilientGithubWrapper) providing actual files of " + repo + " repository...");

		ds.onNext("fileOf:" + repo);

	}

	//
	public void stubDenoteCompletion() {
		ds.onComplete();
	}

}
