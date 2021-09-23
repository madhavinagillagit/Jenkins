package org.conjur.jenkins.configuration;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.Nonnull;

import org.conjur.jenkins.api.ConjurAPIUtils;
import org.kohsuke.stapler.DataBoundSetter;

import hudson.Extension;
import hudson.remoting.Channel;
import jenkins.model.GlobalConfiguration;

/**
 * Example of Jenkins global configuration.
 */
@Extension
public class GlobalConjurConfiguration extends GlobalConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ConjurConfiguration conjurConfiguration;
	private Boolean enableJWKS = false;
	private String authWebServiceId = "";
	private String jwtAudience = "";
	private long keyLifetimeInMinutes = 60;
	private long tokenDurarionInSeconds = 120;

	static Logger getLogger() {
		return Logger.getLogger(GlobalConjurConfiguration.class.getName());
	}

	/** @return the singleton instance */
	@Nonnull
	public static GlobalConjurConfiguration get() {
		Channel channel = Channel.current();

		GlobalConjurConfiguration result = null;
		if (channel == null) {
			result = GlobalConfiguration.all().get(GlobalConjurConfiguration.class);
		} else {
			result = (GlobalConjurConfiguration) ConjurAPIUtils.objectFromMaster(channel,
					new ConjurAPIUtils.NewGlobalConfiguration());
		}
		if (result == null) {
			throw new IllegalStateException();
		}
		return result;
	}

	public GlobalConjurConfiguration() {
		// When Jenkins is restarted, load any saved configuration from disk.
		load();
	}

	public ConjurConfiguration getConjurConfiguration() {
		return conjurConfiguration;
	}

	public Boolean getEnableJWKS() {
		return enableJWKS;
	}

	public String getAuthWebServiceId() {
		return authWebServiceId;
	}

	@DataBoundSetter
	public void setAuthWebServiceId(String authWebServiceId) {
		this.authWebServiceId = authWebServiceId;
		save();
	}

	public String getJwtAudience() {
		return jwtAudience;
	}

	@DataBoundSetter
	public void setJwtAudience(String jwtAudience) {
		this.jwtAudience = jwtAudience;
		save();
	}

	public long getKeyLifetimeInMinutes() {
		return keyLifetimeInMinutes;
	}

	@DataBoundSetter
	public void setKeyLifetimeInMinutes(long keyLifetimeInMinutes) {
		this.keyLifetimeInMinutes = keyLifetimeInMinutes;
		save();
	}

	public long getTokenDurarionInSeconds() {
		return tokenDurarionInSeconds;
	}

	@DataBoundSetter
	public void setTokenDurarionInSeconds(long tokenDurarionInSeconds) {
		this.tokenDurarionInSeconds = tokenDurarionInSeconds;
		save();
	}

	@DataBoundSetter
	public void setConjurConfiguration(ConjurConfiguration conjurConfiguration) {
		this.conjurConfiguration = conjurConfiguration;
		save();
	}

	@DataBoundSetter
	public void setEnableJWKS(Boolean enableJWKS) {
		this.enableJWKS = enableJWKS;
		save();
	}

}
