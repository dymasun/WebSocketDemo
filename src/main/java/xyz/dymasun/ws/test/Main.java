package xyz.dymasun.ws.test;

import xyz.dymasun.ws.rule.RuleMap;
import xyz.dymasun.ws.rule.RuleWorker;

public class Main {
    public static void main(String[] args) {
        new RuleWorker() {
            public void doMethod(RuleMap param) {
                System.out.println("my name is "+param.get("my name"));
                System.out.println("her name is "+param.get("her name"));
            }
        }.pool(RuleMap.create()
                .put("my name","李雷")
                .put("her name","韩梅梅")
        );
        RuleWorker.distory();
    }
}
