package com.test.method;

import org.junit.BeforeClass;

import com.jsql.model.InjectionModel;
import com.jsql.model.MediatorModel;
import com.jsql.model.exception.InjectionFailureException;
import com.jsql.model.injection.method.MethodInjection;
import com.jsql.model.injection.strategy.Strategy;
import com.jsql.util.ConnectionUtil;
import com.jsql.view.terminal.SystemOutTerminal;
import com.test.AbstractTestSuite;
import com.test.mysql.ConcreteMysqlTestSuite;

public class PostTest extends ConcreteMysqlTestSuite {

    @BeforeClass
    public static void initialize() throws InjectionFailureException {
        InjectionModel model = new InjectionModel();
        MediatorModel.register(model);
        model.sendVersionToView();

        MediatorModel.model().addObserver(new SystemOutTerminal());

        ConnectionUtil.urlBase = "http://"+ AbstractTestSuite.hostName +"/simulate_post.php";
        ConnectionUtil.dataRequest = "lib=0";
        ConnectionUtil.methodInjection = MethodInjection.REQUEST;

        MediatorModel.model().injection();

        MediatorModel.model().setStrategy(Strategy.NORMAL);
    }
}