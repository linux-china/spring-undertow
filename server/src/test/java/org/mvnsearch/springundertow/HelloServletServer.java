package org.mvnsearch.springundertow;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.util.Headers;
import org.mvnsearch.springundertow.demo.HelloServlet;

import static io.undertow.servlet.Servlets.defaultContainer;
import static io.undertow.servlet.Servlets.deployment;
import static io.undertow.servlet.Servlets.servlet;

/**
 * Hello servlet server
 *
 * @author linux_china
 */
public class HelloServletServer {

    public static void main(String[] args) throws Exception {
        DeploymentInfo deploymentInfo = deployment()
                .setClassLoader(HelloServletServer.class.getClassLoader())
                .setContextPath("").setDeploymentName("helloApp");
        deploymentInfo.addServlets(servlet("hello", HelloServlet.class).addMapping("/*"));

        DeploymentManager manager = defaultContainer().addDeployment(deploymentInfo);
        manager.deploy();

        Undertow server = Undertow.builder().addListener(8080, "localhost").setHandler(manager.start()).build();
        server.start();

    }
}
