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
package bmi;

/**
 * Simple BMI Java binding. Currently only supports double and float values, and treats 0, 1, 2, and 3 dimensional values as simple 1
 * dimensional arrays.
 *
 * @author Niels Drost
 *
 */
public interface BMI {

    public void initialize(String file) throws BMIModelException;

    public void update() throws BMIModelException;

    public void updateUntil(double time) throws BMIModelException;

    public void updateFrac(double timeFrac) throws BMIModelException;

    /**
     * As "finalize" is reserved in Java, we use finalizeModel instead.
     */
    public void finalizeModel() throws BMIModelException;

    public String getComponentName() throws BMIModelException;

    public String[] getInputVarNames() throws BMIModelException;

    public String[] getOutputVarNames() throws BMIModelException;

    public String getVarType(String longVarName) throws BMIModelException;

    public String getVarUnits(String longVarName) throws BMIModelException;

    public int getVarRank(String longVarName) throws BMIModelException;

    public int getVarSize(String longVarName) throws BMIModelException;

    public int getVarNbytes(String longVarName) throws BMIModelException;

    public double getStartTime() throws BMIModelException;

    public double getCurrentTime() throws BMIModelException;

    public double getEndTime() throws BMIModelException;

    public double getTimeStep() throws BMIModelException;

    public String getTimeUnits() throws BMIModelException;

    public double[] getDouble(String longVarName) throws BMIModelException;

    public double[] getDoubleAtIndices(String longVarName, int[] indices) throws BMIModelException;

    public void setDouble(String longVarName, double[] src) throws BMIModelException;

    public void setDoubleAtIndices(String longVarName, int[] indices, double[] src) throws BMIModelException;

    public float[] getFloat(String longVarName) throws BMIModelException;

    public float[] getFloatAtIndices(String longVarName, int[] indices) throws BMIModelException;

    public void setFloat(String longVarName, float[] src) throws BMIModelException;

    public void setFloatAtIndices(String longVarName, int[] indices, float[] src) throws BMIModelException;

    public BMIGridType getGridType(String longVarName) throws BMIModelException;

    public int[] getGridShape(String longVarName) throws BMIModelException;

    public double[] getGridSpacing(String longVarName) throws BMIModelException;

    public double[] getGridOrigin(String longVarName) throws BMIModelException;

    public double[] getGridX(String longVarName) throws BMIModelException;

    public double[] getGridY(String longVarName) throws BMIModelException;

    public double[] getGridZ(String longVarName) throws BMIModelException;

    public int[] getGridConnectivity(String longVarName) throws BMIModelException;

    public int[] getGridOffset(String longVarName) throws BMIModelException;

}
