package de.buehmann.gradle.jubula;

public class JubulaPluginExtension {

	protected static final String NAME = "jubula";

	private static final String DEFAULT_TOOLKIT = "swing";

	private String toolkit = DEFAULT_TOOLKIT;

	/**
	 * @return The toolkit to use with Jubula.
	 */
	public String getToolkit() {
		return toolkit;
	}

	/**
	 * @param toolkit The toolkit to use with Jubula.
	 */
	public void setToolkit(final String toolkit) {
		this.toolkit = toolkit;
	}

}
