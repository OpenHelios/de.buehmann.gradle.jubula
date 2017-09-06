package de.buehmann.gradle.jubula;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.ProjectEvaluationListener;
import org.gradle.api.ProjectState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JubulaPlugin implements Plugin<Project>, ProjectEvaluationListener {

	private static final Logger LOG = LoggerFactory.getLogger(JubulaPlugin.class);

	private static final String TEST_COMPILE = "testCompile";

	@Override
	public void apply(final Project project) {
		project.getExtensions().create(JubulaPluginExtension.NAME, JubulaPluginExtension.class);
		project.getGradle().addProjectEvaluationListener(this);
	}

	@Override
	public void afterEvaluate(final Project project, final ProjectState state) {
		if (!state.getExecuted() || null != state.getFailure()) {
			return;
		}
		// add dependency to Jubula API runner
		project.getDependencies().add(TEST_COMPILE, "de.buehmann.tools:de.buehmann.jubula.api.runner:4.1.+");
		// add Jubula toolkit specific dependency
		final String toolkit = project.getExtensions().getByType(JubulaPluginExtension.class).getToolkit()
				.toLowerCase();
		final StringBuilder sb = new StringBuilder("org.eclipse.jubula:org.eclipse.jubula.toolkit.");
		sb.append(toolkit).appen(".api:");
		if ("javafx".equals(toolkit)) {
			sb.append("4.1.0");
		} else {
			sb.append("4.0.0");
		}
		sb.append("-SNAPSHOT");
		project.getDependencies().add(TEST_COMPILE, sb.toString());
	}

	@Override
	public void beforeEvaluate(final Project project) {
	}

}
