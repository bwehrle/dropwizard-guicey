package ru.vyarus.dropwizard.guice.support.order

import io.dropwizard.lifecycle.Managed
import ru.vyarus.dropwizard.guice.module.installer.order.Order

/**
 * @author Vyacheslav Rusakov 
 * @since 13.10.2014
 */
@Order(3)
class Ext2 implements Managed {

    @Override
    void start() throws Exception {

    }

    @Override
    void stop() throws Exception {

    }
}
