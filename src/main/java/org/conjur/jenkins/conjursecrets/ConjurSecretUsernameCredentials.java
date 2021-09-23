package org.conjur.jenkins.conjursecrets;

import com.cloudbees.plugins.credentials.common.StandardUsernamePasswordCredentials;

import org.conjur.jenkins.configuration.ConjurConfiguration;

import hudson.model.Item;
import hudson.model.Run;
import hudson.util.Secret;

// @NameWith(value = ConjurSecretUsernameCredentials.NameProvider.class, priority = 1)

public interface ConjurSecretUsernameCredentials extends StandardUsernamePasswordCredentials, ConjurSecretCredentials {

	String getDisplayName();

	void setContext(Run<?, ?> context);

	Secret getSecret();

	void setConjurConfiguration(ConjurConfiguration conjurConfiguration);

}
