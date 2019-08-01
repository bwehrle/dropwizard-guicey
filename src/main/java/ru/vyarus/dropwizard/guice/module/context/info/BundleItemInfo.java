package ru.vyarus.dropwizard.guice.module.context.info;

import ru.vyarus.dropwizard.guice.module.context.info.sign.DisableSupport;

/**
 * Base interface for bundle items: guicey and dropwizard bundles.
 *
 * @author Vyacheslav Rusakov
 * @since 28.07.2019
 */
public interface BundleItemInfo extends InstanceItemInfo, DisableSupport {

    /**
     * In case when bundle is registered multiple times, bundle will be transitive if all registrations were transitive.
     *
     * @return true when bundle was registered only by some other bundle (and never directly)
     */
    boolean isTransitive();

    /**
     *
     * @return true for dropwizard bundle, false for guicey bundle
     */
    boolean isDropwizard();
}
