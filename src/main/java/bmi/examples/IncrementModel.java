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
package bmi.examples;

import bmi.BMIGridType;
import bmi.BMIModelException;
import bmi.EBMI;

/**
 * Model that holds only a single (grid) variable, which increments at every timestep.
 * 
 * 
 * @author Rolf Hut
 * @author Niels Drost
 *
 */
public class IncrementModel implements EBMI {
    private double dt;
    private double t;
    private double startTime;
    private double endTime;
    private DoubleRaster state = null;

    private final int[] shape;
    private final String name = "Example java toy increment Model";

    public IncrementModel() {
        shape = new int[] { 10, 10 };
    }

    @Override
    public void initializeConfig(String configFile) throws BMIModelException {
        //file not actually used

        dt = 1.0;
        startTime = 1.0;
        t = startTime;
        endTime = 20.0;
    }

    @Override
    public void initializeState(String sourceDirectory) throws BMIModelException {
        //initialize state
        state = new DoubleRaster(shape[0], shape[1]);
        state.setScalar(startTime);
    }

    @Override
    public void initialize(String file) throws BMIModelException {
        initializeConfig(file);
        initializeState(null);
    }

    @Override
    public void update() throws BMIModelException {
        if (t >= endTime) {
            throw new BMIModelException("endTime already reached, model not updated");
        }
        state.addScalar(1);
        t += dt;
    };

    @Override
    public void updateUntil(double time) throws BMIModelException {
        if ((time < t) | (time > endTime)) {
            throw new BMIModelException("wrong time input: smaller than model time or larger than endTime");
        }
        while (t < time) {
            update();
        }
    };

    @Override
    public void finalizeModel() {
        dt = 0;
        t = 0;
        state = null;
    };

    @Override
    public String[] getInputVarNames() {
        return new String[] { "var1" };
    };

    @Override
    public String[] getOutputVarNames() {
        return new String[] { "var1" };
    };

    @Override
    public String getVarType(String longVarName) throws BMIModelException {
        if (!longVarName.equals("var1")) {
            throw new BMIModelException("variable " + longVarName + " does not exist");
        }
        return "float64";
    };

    @Override
    public String getVarUnits(String longVarName) throws BMIModelException {
        if (!longVarName.equals("var1")) {
            throw new BMIModelException("variable " + longVarName + " does not exist");
        }
        return "-";
    };

    @Override
    public int getVarRank(String longVarName) throws BMIModelException {
        if (!longVarName.equals("var1")) {
            throw new BMIModelException("variable " + longVarName + " does not exist");
        }
        return state.getRank();
    };

    @Override
    public double getStartTime() {
        return startTime;
    };

    @Override
    public double getEndTime() {
        return endTime;
    };

    @Override
    public double getCurrentTime() {
        return t;
    }

    @Override
    public String getComponentName() {
        return this.name;
    }

    @Override
    public double[] getDouble(String long_var_name) throws BMIModelException {
        if (!long_var_name.equals("var1")) {
            throw new BMIModelException("variable " + long_var_name + " does not exist");
        }

        return state.getValues();
    }

    @Override
    public double[] getDoubleAtIndices(String long_var_name, int[] indices) throws BMIModelException {
        if (!long_var_name.equals("var1")) {
            throw new BMIModelException("variable " + long_var_name + " does not exist");
        }

        return state.getValues(indices);
    }

    @Override
    public void setDouble(String long_var_name, double[] src) throws BMIModelException {
        if (!long_var_name.equals("var1")) {
            throw new BMIModelException("variable " + long_var_name + " does not exist");
        }

        state.setValues(src);
    }

    @Override
    public void setDoubleAtIndices(String long_var_name, int[] indices, double[] src) throws BMIModelException {
        if (!long_var_name.equals("var1")) {
            throw new BMIModelException("variable " + long_var_name + " does not exist");
        }

        state.setValues(indices, src);
    }

    @Override
    public BMIGridType getGridType(String longVarName) throws BMIModelException {
        if (!longVarName.equals("var1")) {
            throw new BMIModelException("variable " + longVarName + " does not exist");
        }
        return BMIGridType.UNIFORM;
    }

    @Override
    public int[] getGridShape(String longVarName) throws BMIModelException {
        if (!longVarName.equals("var1")) {
            throw new BMIModelException("variable " + longVarName + " does not exist");
        }
        return shape;
    }

    @Override
    public double[] getGridSpacing(String longVarName) throws BMIModelException {
        if (!longVarName.equals("var1")) {
            throw new BMIModelException("variable " + longVarName + " does not exist");
        }
        return new double[] { 1.0, 1.0 };
    }

    @Override
    public double[] getGridOrigin(String longVarName) throws BMIModelException {
        if (!longVarName.equals("var1")) {
            throw new BMIModelException("variable " + longVarName + " does not exist");
        }
        return new double[] { 0.0, 0.0 };
    }

    /* (non-Javadoc)
     * @see nl.esciencecenter.bmi.BMI#get_float(java.lang.String)
     */
    @Override
    public float[] getFloat(String longVarName) throws BMIModelException {
        throw new BMIModelException("model does not use float values");
    }

    /* (non-Javadoc)
     * @see nl.esciencecenter.bmi.BMI#get_float_at_indices(java.lang.String, int[])
     */
    @Override
    public float[] getFloatAtIndices(String longVarName, int[] indices) throws BMIModelException {
        throw new BMIModelException("model does not use float values");
    }

    /* (non-Javadoc)
     * @see nl.esciencecenter.bmi.BMI#set_float(java.lang.String, float[])
     */
    @Override
    public void setFloat(String longVarName, float[] src) throws BMIModelException {
        throw new BMIModelException("model does not use float values");
    }

    /* (non-Javadoc)
     * @see nl.esciencecenter.bmi.BMI#set_float_at_indices(java.lang.String, int[], float[])
     */
    @Override
    public void setFloatAtIndices(String longVarName, int[] indices, float[] src) throws BMIModelException {
        throw new BMIModelException("model does not use float values");
    }

    @Override
    public void updateFrac(double timeFrac) throws BMIModelException {
        throw new BMIModelException("model does not support this function");
    }

    @Override
    public void saveState(String destinationFolder) throws BMIModelException {
        throw new BMIModelException("model does not support saving state");
    }

    @Override
    public int getVarSize(String longVarName) throws BMIModelException {
        int result = 1;

        for (int size : shape) {
            result *= size;
        }

        return result;
    }

    @Override
    public int getVarNbytes(String longVarName) throws BMIModelException {
        return getVarSize(longVarName) * (Double.SIZE / 8);
    }

    @Override
    public double getTimeStep() throws BMIModelException {
        return 1;
    }

    @Override
    public String getTimeUnits() throws BMIModelException {
        return "seconds";
    }

    @Override
    public double[] getGridX(String longVarName) throws BMIModelException {
        throw new BMIModelException("model only supports raster variables");
    }

    @Override
    public double[] getGridY(String longVarName) throws BMIModelException {
        throw new BMIModelException("model only supports raster variables");
    }

    @Override
    public double[] getGridZ(String longVarName) throws BMIModelException {
        throw new BMIModelException("model only supports raster variables");
    }

    @Override
    public int[] getGridConnectivity(String longVarName) throws BMIModelException {
        throw new BMIModelException("model only supports raster variables");
    }

    @Override
    public int[] getGridOffset(String longVarName) throws BMIModelException {
        throw new BMIModelException("model only supports raster variables");
    }

    @Override
    public void setStartTime(double startTime) throws BMIModelException {
        if (this.state != null) {
            throw new BMIModelException("cannot set start time after model has been initialized");
        }

        this.startTime = startTime;
    }

    @Override
    public void setEndTime(double endTime) throws BMIModelException {
        if (this.state != null) {
            throw new BMIModelException("cannot set start time after model has been initialized");
        }

        this.endTime = endTime;
    }

    @Override
    public String[] getAttributeNames() throws BMIModelException {
        return new String[] { "author" };
    }

    @Override
    public String getAttributeValue(String attributeName) throws BMIModelException {
        if (attributeName.equals("author")) {
            return "Rolf Hut";
        } else {
            throw new BMIModelException("unknown attribute " + attributeName);
        }
    }

    @Override
    public String setAttributeValue(String attributeName, String attributeValue) throws BMIModelException {
        throw new BMIModelException("no settable attributes in this model");
    }
}
