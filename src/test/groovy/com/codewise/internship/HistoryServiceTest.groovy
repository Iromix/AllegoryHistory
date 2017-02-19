package com.codewise.internship

import spock.lang.Specification
import spock.util.concurrent.PollingConditions

import java.util.concurrent.Executor
import java.util.concurrent.Executors


public class HistoryServiceTest extends Specification {

    def "should put money and get average"() {
        given:
        HistoryService historyService = new HistoryService()
        ID id = new ID(1)

        when:
        historyService.put(id, 10.0)
        historyService.put(id, 30.0)
        double average = historyService.get(id)

        then:
        average == 20.0
    }

    def "should get 0 average with no money put"() {
        given:
        HistoryService historyService = new HistoryService()
        ID id = new ID(1)

        expect:
        historyService.get(id) == 0
    }

    def "should put money by clients running in thread and get average"() {
        def pollConditions = new PollingConditions(timeout: 1)

        given:
        ID id1 = new ID(1);
        ID id2 = new ID(2);
        HistoryService historyService = new HistoryService()

        when:
        Executor executor = Executors.newFixedThreadPool(2);
        executor.submit({
            historyService.put(id1, 5);
        })
        executor.submit({
            historyService.put(id1, 15);
        })
        executor.submit({
            historyService.put(id2, 5);
        })
        executor.submit({
            historyService.put(id2, 35);
        })

        then:
        pollConditions.within(1) {
            assert historyService.get(id1) == 10
            assert historyService.get(id2) == 20
        }
    }

    //TODO simulate big traffic

}