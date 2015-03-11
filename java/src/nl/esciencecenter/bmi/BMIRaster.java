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

public interface BMIRaster extends BMI {

    public int[] get_grid_shape(String long_var_name) throws BMIModelException;

    public double[] get_grid_spacing(String long_var_name) throws BMIModelException;

    public double[] get_grid_origin(String long_var_name) throws BMIModelException;

}
