/*
 * Copyright 2014 Netherlands eScience Center
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.esciencecenter.bmi;

public enum BMIGridType {

    UNKNOWN(0),
    UNIFORM(1),
    RECTILINEAR(2),
    STRUCTURED(3),
    UNSTRUCTURED(4);

    private final int value;

    private BMIGridType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BMIGridType findByValue(int value) { 
        switch (value) {
            case 0:
                return UNKNOWN;
            case 1:
                return UNIFORM;
            case 2:
                return RECTILINEAR;
            case 3:
                return STRUCTURED;
            case 4:
                return UNSTRUCTURED;
            default:
                throw new IllegalArgumentException("Unknown BmiGridType passed: " + value);
        }
    }
}
