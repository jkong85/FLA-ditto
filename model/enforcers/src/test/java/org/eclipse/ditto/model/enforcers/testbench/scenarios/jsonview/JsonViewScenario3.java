/*
 * Copyright (c) 2017 Bosch Software Innovations GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/index.php
 *
 * Contributors:
 *    Bosch Software Innovations GmbH - initial contribution
 */
package org.eclipse.ditto.model.enforcers.testbench.scenarios.jsonview;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.ditto.json.JsonFieldSelector;
import org.eclipse.ditto.model.policies.SubjectId;
import org.eclipse.ditto.model.policies.SubjectIssuer;
import org.eclipse.ditto.model.enforcers.testbench.scenarios.Scenario;
import org.eclipse.ditto.model.enforcers.testbench.scenarios.ScenarioSetup;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;


@State(Scope.Benchmark)
public class JsonViewScenario3 implements JsonViewScenario {

    private final ScenarioSetup setup;

    public JsonViewScenario3() {
        setup = Scenario.newScenarioSetup( //
                true, //
                "Subject has READ+WRITE granted on '/'. "
                        + "Subject has READ+WRITE revoked on '/attributes'. " //
                        + "Subject has READ granted on '/attributes/location'. " //
                        + "Is able to READ '/'. Can see in JsonView: '/attributes/location' and '/features'.", //
                getPolicy(), //
                Scenario.newAuthorizationContext(SUBJECT_ALL_GRANTED_ATTRIBUTES_REVOKED), //
                "/", //
                THING, //
                THING.toJson(JsonFieldSelector.newInstance("/attributes/location", "/features")), //
                Stream.of(
                        SubjectId.newInstance(SubjectIssuer.GOOGLE, SUBJECT_ALL_GRANTED).toString())
                        .collect(Collectors.toSet()), //
                "READ");
    }

    @Override
    public ScenarioSetup getSetup() {
        return setup;
    }
}
