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

/**
 * Simple BMI Java binding. Currently only supports double values, and treats 0, 1, 2, and 3 dimensional values as simple 1
 * dimensional arrays.
 *
 * @author Niels Drost
 *
 */
public interface BMI {

    public void initialize(String file) throws BMIModelException;

    public void update() throws BMIModelException;

    public void update_until(double time) throws BMIModelException;

    public void update_frac(double time_frac) throws BMIModelException;

    public void save_state(double destination_folder) throws BMIModelException;

    /**
     * As "finalize" is reserved in Java, we use finalize_model instead.
     */
    public void finalize_model() throws BMIModelException;

    public String get_component_name() throws BMIModelException;

    public String[] get_input_var_names() throws BMIModelException;

    public String[] get_output_var_names() throws BMIModelException;

    public String get_var_type(String long_var_name) throws BMIModelException;

    public String get_var_units(String long_var_name) throws BMIModelException;

    public int get_var_rank(String long_var_name) throws BMIModelException;

    public int get_var_size(String long_var_name) throws BMIModelException;

    public int get_var_nbytes(String long_var_name) throws BMIModelException;

    public double get_start_time() throws BMIModelException;

    public double get_current_time() throws BMIModelException;

    public double get_end_time() throws BMIModelException;

    public double get_time_step() throws BMIModelException;

    public String get_time_units() throws BMIModelException;

    public double[] get_double(String long_var_name) throws BMIModelException;

    public double[] get_double_at_indices(String long_var_name, int[] indices) throws BMIModelException;

    public void set_double(String long_var_name, double[] src) throws BMIModelException;

    public void set_double_at_indices(String long_var_name, int[] indices, double[] src) throws BMIModelException;

    public float[] get_float(String long_var_name) throws BMIModelException;

    public float[] get_float_at_indices(String long_var_name, int[] indices) throws BMIModelException;

    public void set_float(String long_var_name, float[] src) throws BMIModelException;

    public void set_float_at_indices(String long_var_name, int[] indices, float[] src) throws BMIModelException;

    public BMIGridType get_grid_type(String long_var_name) throws BMIModelException;

    public int[] get_grid_shape(String long_var_name) throws BMIModelException;

    public double[] get_grid_spacing(String long_var_name) throws BMIModelException;

    public double[] get_grid_origin(String long_var_name) throws BMIModelException;

    public double[] get_grid_x(String long_var_name) throws BMIModelException;

    public double[] get_grid_y(String long_var_name) throws BMIModelException;

    public double[] get_grid_z(String long_var_name) throws BMIModelException;

    public double[] get_grid_connectivity(String long_var_name) throws BMIModelException;

    public double[] get_grid_offset(String long_var_name) throws BMIModelException;

}
