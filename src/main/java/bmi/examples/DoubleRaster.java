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

public class DoubleRaster {

    private final double[] data;
    private final int width;
    private final int height;

    public DoubleRaster(int x, int y) {
        data = new double[x * y];
        this.width = x;
        this.height = y;
    }

    public void setScalar(double scalar) {
        for (int i = 0; i < data.length; i++) {
            data[i] = scalar;
        }
    }

    public void addScalar(double scalar) {
        for (int i = 0; i < data.length; i++) {
            data[i] += scalar;
        }
    }

    public int getRank() {
        return 2;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }

    public double[] getValues() {
        return data;
    }

    public double[] getValues(int[] indices) {
        double[] result = new double[indices.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = data[indices[i]];
        }

        return result;
    }

    public void setValues(double[] src) {
        System.arraycopy(src, 0, data, 0, src.length);
    }

    public void setValues(int[] indices, double[] src) {
        for (int i = 0; i < indices.length; i++) {
            data[indices[i]] = src[i];
        }
    }

}