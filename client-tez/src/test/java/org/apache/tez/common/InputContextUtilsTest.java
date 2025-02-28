/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tez.common;

import org.apache.tez.dag.records.TezTaskAttemptID;
import org.apache.tez.runtime.api.InputContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputContextUtilsTest {

  @Test
  public void testGetTezTaskAttemptID() {
    InputContext inputContext = mock(InputContext.class);
    when(inputContext.getUniqueIdentifier()).thenReturn("attempt_1685094627632_0157_1_01_000000_0_10006");

    TezTaskAttemptID rightTaskAttemptID = TezTaskAttemptID.fromString("attempt_1685094627632_0157_1_01_000000_0");
    assertEquals(rightTaskAttemptID, InputContextUtils.getTezTaskAttemptID(inputContext));
  }


  @Test
  public void testComputeShuffleId() {
    InputContext inputContext = mock(InputContext.class);
    when(inputContext.getDagIdentifier()).thenReturn(1);
    when(inputContext.getSourceVertexName()).thenReturn("Map 1");
    when(inputContext.getTaskVertexName()).thenReturn("Reducer 1");

    assertEquals(1001601, InputContextUtils.computeShuffleId(inputContext));
  }
}
