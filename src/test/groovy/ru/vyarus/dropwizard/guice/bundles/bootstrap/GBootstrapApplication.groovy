package ru.vyarus.dropwizard.guice.bundles.bootstrap

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import ru.vyarus.dropwizard.guice.GuiceBundle
import ru.vyarus.dropwizard.guice.module.installer.CoreInstallersBundle
import ru.vyarus.dropwizard.guice.module.installer.feature.LifeCycleInstaller
import ru.vyarus.dropwizard.guice.module.installer.feature.health.HealthCheckInstaller
import ru.vyarus.dropwizard.guice.module.installer.bundle.GuiceyBootstrap
import ru.vyarus.dropwizard.guice.module.installer.bundle.GuiceyBundle
import ru.vyarus.dropwizard.guice.support.TestConfiguration
import ru.vyarus.dropwizard.guice.support.feature.DummyManaged
import ru.vyarus.dropwizard.guice.support.feature.DummyResource
import ru.vyarus.dropwizard.guice.support.feature.DummyService
import ru.vyarus.dropwizard.guice.support.feature.DummyTask

/**
 * @author Vyacheslav Rusakov 
 * @since 02.08.2015
 */
class GBootstrapApplication extends Application<TestConfiguration> {

    @Override
    void initialize(Bootstrap<TestConfiguration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.<TestConfiguration> builder()
                .bundles(
                new CoreInstallersBundle(),
                new GuiceyBundle() {
                    @Override
                    void initialize(GuiceyBootstrap gbootstrap) {
                        assert gbootstrap.configuration() !=null
                        assert gbootstrap.environment() !=null
                        assert gbootstrap.application() !=null

                        gbootstrap
                        .disableInstallers(LifeCycleInstaller, HealthCheckInstaller)
                        .extensions(DummyTask, DummyResource, DummyManaged)
                        .modules(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bindConstant().annotatedWith(Names.named("sample")).to("test str")
                                bind(DummyService)
                            }
                        })
                    }
                })
                .extensions(DummyTask, DummyResource, DummyManaged)
                .build()
        );
    }

    @Override
    void run(TestConfiguration configuration, Environment environment) throws Exception {
    }
}
