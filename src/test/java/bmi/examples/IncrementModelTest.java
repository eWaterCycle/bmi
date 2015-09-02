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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bmi.EBMI;
import bmi.BMIGridType;
import bmi.BMIModelException;
import bmi.examples.IncrementModel;

public class IncrementModelTest {

    //fixture
    private EBMI model;

    @Before
    public void setUp() throws BMIModelException {
        this.model = new IncrementModel();

        this.model.initialize("");
    }

    @After
    public void tearDown() {
        try {
            this.model.finalizeModel();
        } catch (BMIModelException e) {
            //IGNORE
        }

        this.model = null;
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#IncrementModel()}.
     * 
     * @throws Exception
     */
    public void testIncrementModel() throws Exception {
       new IncrementModel();
    }
    

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#initialize(java.lang.String)}.
     * 
     * @throws Exception
     */
    public void testInitialize() throws Exception {
        EBMI model = new IncrementModel();

        model.initialize("");
    }
    
    @Test
    public void testInitializeConfig() throws Exception {
        EBMI model = new IncrementModel();

        model.initializeConfig("");
    }

    @Test
    public void testInitializeState() throws Exception {
        EBMI model = new IncrementModel();

        model.initializeConfig("");
        
        model.initializeState(null);
    }


    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#update()}.
     * 
     * @throws Exception
     */
    public void testUpdate() throws Exception {
        double startTime = model.getCurrentTime();
        double startValue = model.getDouble("var1")[0];

        assertEquals(1.0, startTime, 0.0);
        assertEquals(1.0, startValue, 0.0);

        model.update();

        double endTime = model.getCurrentTime();
        double endValue = model.getDouble("var1")[0];

        assertEquals(2.0, endTime, 0.0);
        assertEquals(2.0, endValue, 0.0);
    }

    @Test(expected = BMIModelException.class)
    public void testUpdate_AfterEndTime_Exception() throws Exception {
        model.updateUntil(model.getEndTime());

        model.update();
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#updateUntil(double)}.
     * 
     * @throws Throwable
     */
    public void testUpdateUntil() throws Throwable {

        double startTime = model.getCurrentTime();
        double startValue = model.getDouble("var1")[0];

        assertEquals(1.0, startTime, 0.0);
        assertEquals(1.0, startValue, 0.0);

        model.updateUntil(16.0);

        double endTime = model.getCurrentTime();
        double endValue = model.getDouble("var1")[0];

        assertEquals(16.0, endTime, 0.0);
        assertEquals(16.0, endValue, 0.0);
    }

    @Test(expected = BMIModelException.class)
    public void testUpdateUntil_AfterEndTime_Exception() throws Exception {
        model.updateUntil(100000.0);
    }

    @Test(expected = BMIModelException.class)
    public void testUpdateUntil_BeforeCurrentTime_Exception() throws Exception {
        model.updateUntil(-100000.0);

    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#finalizeModel()}.
     * 
     * @throws Exception
     */
    @Test
    public void testFinalizeModel() throws Exception {
        model.getDouble("var1");
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getInputVarNames()}.
     * 
     * @throws Exception
     *             in case of a failure
     */
    @Test
    public void testGetInputVarNames() throws Exception {

        String[] result = model.getInputVarNames();
        String[] expected = new String[] { "var1" };

        assertTrue(Arrays.equals(expected, result));

    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getOutputVarNames()}.
     * 
     * @throws Exception
     *             in case of a failure
     */
    @Test
    public void testGetOutputVarNames() throws Exception {

        String[] result = model.getOutputVarNames();
        String[] expected = new String[] { "var1" };

        assertTrue(Arrays.equals(expected, result));

    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getVarType(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetVarType() throws Exception {

        String result = model.getVarType("var1");

        assertEquals("float64", result);
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getVarType(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test(expected = BMIModelException.class)
    public void testGetVarType_InvalidVariable_Exception() throws Exception {
        model.getVarType("doesNotExistVar");
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getVarUnits(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetVarUnits() throws Exception {

        String result = model.getVarUnits("var1");

        assertEquals("-", result);
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getVarUnits(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test(expected = BMIModelException.class)
    public void testGetVarUnitsInvalidVariableException() throws Exception {
        model.getVarUnits("doesNotExistVar");
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getVarRank(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetVar_rank() throws Exception {

        int result = model.getVarRank("var1");

        assertEquals(2, result);
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getVarRank(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test(expected = BMIModelException.class)
    public void testGetVarRank_InvalidVariable_Exception() throws Exception {

        model.getVarRank("doesNotExistVar");
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getStartTime()}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetStartTime() throws Exception {

        double result = model.getStartTime();

        assertEquals(1.0, result, 0.0);

    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getEndTime()}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetEndTime() throws Exception {

        double result = model.getEndTime();

        assertEquals(20.0, result, 0.0);

    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getCurrentTime()}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetCurrentTime() throws Exception {

        model.update();
        model.update();

        double result = model.getCurrentTime();

        assertEquals(3.0, result, 0.0);

    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getComponentName()}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetComponentName() throws Exception {

        String expected = "Example java toy increment Model";
        String result = model.getComponentName();

        assertEquals(expected, result);
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getDouble(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetDouble() throws Exception {

        model.update();

        double[] result = model.getDouble("var1");

        double expected = 2.0;

        assertEquals(100, result.length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(expected, result[i], 0.0);
        }
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getDouble(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test(expected = BMIModelException.class)
    public void testGetDouble_InvalidVariable_Exception() throws Exception {
        model.getDouble("doesNotExistVar");
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getDoubleAtIndices(java.lang.String, int[])}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetDoubleAtIndices() throws Exception {
        model.update();

        int[] indices = new int[] { 4, 5, 6, 7, 8 };

        double[] result = model.getDoubleAtIndices("var1", indices);

        double expected = 2.0;

        assertEquals(5, result.length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(expected, result[i], 0.0);
        }
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getDouble(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test(expected = BMIModelException.class)
    public void testGetDoubleAtIndices_InvalidVariable_Exception() throws Exception {
        int[] indices = new int[] { 4, 5, 6, 7, 8 };

        model.getDoubleAtIndices("doesNotExistVar", indices);
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#setDouble(java.lang.String, double[])}.
     * 
     * @throws Exception
     */
    @Test
    public void testSetDouble() throws Exception {
        model.updateUntil(10.0);

        double[] data = new double[100];

        model.setDouble("var1", data);

        model.updateUntil(20.0);

        double[] result = model.getDouble("var1");

        double expected = 10.0;

        assertEquals(100, result.length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(expected, result[i], 0.0);
        }
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#setDouble(java.lang.String, double[])}.
     * 
     * @throws Exception
     */
    @Test(expected = BMIModelException.class)
    public void testSetDouble_InvalidVariable_Exception() throws Exception {
        double[] data = new double[100];

        model.setDouble("doesNotExistVar", data);

    }

    /**
     * Test method for
     * {@link nl.esciencecenter.bmi.toymodels.IncrementModel#setDoubleAtIndices(java.lang.String, int[], double[])}.
     * 
     * @throws Exception
     */
    @Test
    public void testSetDoubleAtIndices() throws Exception {
        model.updateUntil(10.0);

        int[] indices = new int[] { 4, 5, 6, 7, 8 };
        double[] values = new double[] { 2.0, 2.0, 2.0, 2.0, 2.0 };

        model.setDoubleAtIndices("var1", indices, values);

        model.updateUntil(20.0);

        double[] result = model.getDouble("var1");

        double expected = 12.0;

        assertEquals(100, result.length);

        //value should be "as normal" in most result indices (including the first)
        assertEquals(20.0, result[0], 0.0);

        System.err.println(Arrays.toString(result));

        //values should be different at set indices
        for (int i = 0; i < indices.length; i++) {
            assertEquals(expected, result[indices[i]], 0.0);
        }
    }

    /**
     * Test method for
     * {@link nl.esciencecenter.bmi.toymodels.IncrementModel#setDoubleAtIndices(java.lang.String, int[], double[])}.
     */
    @Test(expected = BMIModelException.class)
    public void testSetDoubleAtIndices_InvalidVariable_Exception() throws Exception {
        int[] indices = new int[] { 4, 5, 6, 7, 8 };
        double[] values = new double[] { 4., 5., 6., 7., 8. };

        model.setDoubleAtIndices("doesNotExistVar", indices, values);
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getGridType(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetGridType() throws Exception {
        BMIGridType result = model.getGridType("var1");

        assertEquals(BMIGridType.UNIFORM, result);
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getGridType(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test(expected = BMIModelException.class)
    public void testGetGridType_InvalidVariable_Exception() throws Exception {

        model.getGridType("doesNotExistVar");
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getGridShape(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetGridShape() throws Exception {
        int[] expected = new int[] { 10, 10 };

        int[] result = model.getGridShape("var1");

        assertTrue(Arrays.equals(expected, result));

    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getGridShape(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test(expected = BMIModelException.class)
    public void testGetGridShape_InvalidVariable_Exception() throws Exception {
        model.getGridShape("doesNotExistVar");
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getGridSpacing(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetGridSpacing() throws Exception {
        double[] expected = new double[] { 1.0, 1.0 };

        double[] result = model.getGridSpacing("var1");

        assertTrue(Arrays.equals(expected, result));
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getGridSpacing(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test(expected = BMIModelException.class)
    public void testGetGridSpacing_InvalidVariable_Exception() throws Exception {
        model.getGridSpacing("doesNotExistVar");

    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getGridOrigin(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test
    public void testGetGridOrigin() throws Exception {
        double[] expected = new double[] { 0.0, 0.0 };

        double[] result = model.getGridOrigin("var1");

        assertTrue(Arrays.equals(expected, result));
    }

    /**
     * Test method for {@link nl.esciencecenter.bmi.toymodels.IncrementModel#getGridOrigin(java.lang.String)}.
     * 
     * @throws Exception
     */
    @Test(expected = BMIModelException.class)
    public void testGetGridOrigin_InvalidVariable_Exception() throws Exception {
        model.getGridOrigin("doesNotExistVar");

    }

    /**
     * Test method for {@link bmi.BMI#updateFrac(double)}.
     * @throws BMIModelException 
     */
    @Test(expected = BMIModelException.class)
    public void testUpdateFrac() throws BMIModelException {
        model.updateFrac(0.4);
    }

    /**
     * Test method for {@link bmi.BMI#saveState(double)}.
     * @throws BMIModelException 
     */
    @Test(expected = BMIModelException.class)
    public void testSaveState() throws BMIModelException {
        model.saveState("somewhere");
    }

    /**
     * Test method for {@link bmi.BMI#getVarRank(java.lang.String)}.
     * @throws BMIModelException 
     */
    @Test
    public void testGetVarRank() throws BMIModelException {
        assertEquals(2, model.getVarRank("var1"));
    }

    /**
     * Test method for {@link bmi.BMI#getVarSize(java.lang.String)}.
     * @throws BMIModelException 
     */
    @Test
    public void testGetVarSize() throws BMIModelException {
        assertEquals(100, model.getVarSize("var1"));
    }

    /**
     * Test method for {@link bmi.BMI#getVarNbytes(java.lang.String)}.
     * @throws BMIModelException 
     */
    @Test
    public void testGetVarNbytes() throws BMIModelException {
        assertEquals(800, model.getVarNbytes("var1"));
    }

    /**
     * Test method for {@link bmi.BMI#getTimeStep()}.
     * 
     * @throws BMIModelException
     */
    @Test
    public void testGetTimeStep() throws BMIModelException {
        assertEquals(model.getTimeStep(), 1.0, 0.001);
    }

    /**
     * Test method for {@link bmi.BMI#getTimeUnits()}.
     * 
     * @throws BMIModelException
     */
    @Test
    public void testGetTimeUnits() throws BMIModelException {
        assertEquals("seconds", model.getTimeUnits());
    }

    /**
     * Test method for {@link bmi.BMI#getFloat(java.lang.String)}.
     * 
     * @throws BMIModelException
     */
    @Test(expected = BMIModelException.class)
    public void testGetFloat() throws BMIModelException {
        model.getFloat("var1");
    }

    /**
     * Test method for {@link bmi.BMI#getFloatAtIndices(java.lang.String, int[])}.
     * 
     * @throws BMIModelException
     */
    @Test(expected = BMIModelException.class)
    public void testGetFloatAtIndices() throws BMIModelException {
        model.getFloatAtIndices("var1", new int[0]);
    }

    /**
     * Test method for {@link bmi.BMI#setFloat(java.lang.String, float[])}.
     * 
     * @throws BMIModelException
     */
    @Test(expected = BMIModelException.class)
    public void testSetFloat() throws BMIModelException {
        model.setFloat("var1", new float[0]);
    }

    /**
     * Test method for {@link bmi.BMI#setFloatAtIndices(java.lang.String, int[], float[])}.
     * 
     * @throws BMIModelException
     */
    @Test(expected = BMIModelException.class)
    public void testSetFloatAtIndices() throws BMIModelException {
        model.setFloatAtIndices("var1", new int[0], new float[0]);
    }

    /**
     * Test method for {@link bmi.BMI#getGridX(java.lang.String)}.
     * 
     * @throws BMIModelException
     */
    @Test(expected = BMIModelException.class)
    public void testGetGridX() throws BMIModelException {
        model.getGridX("var1");
    }

    /**
     * Test method for {@link bmi.BMI#getGridY(java.lang.String)}.
     * 
     * @throws BMIModelException
     */
    @Test(expected = BMIModelException.class)
    public void testGetGridY() throws BMIModelException {
        model.getGridY("var1");
    }

    /**
     * Test method for {@link bmi.BMI#getGridZ(java.lang.String)}.
     * 
     * @throws BMIModelException
     */
    @Test(expected = BMIModelException.class)
    public void testGetGridZ() throws BMIModelException {
        model.getGridZ("var1");
    }

    /**
     * Test method for {@link bmi.BMI#getGridConnectivity(java.lang.String)}.
     * 
     * @throws BMIModelException
     */
    @Test(expected = BMIModelException.class)
    public void testGetGridConnectivity() throws BMIModelException {
        model.getGridConnectivity("var1");
    }

    /**
     * Test method for {@link bmi.BMI#getGridOffset(java.lang.String)}.
     * 
     * @throws BMIModelException
     */
    @Test(expected = BMIModelException.class)
    public void testGetGridOffset() throws BMIModelException {
        model.getGridOffset("var1");
    }
    
    @Test
    public void testSetStartTime() throws Exception {
        EBMI model = new IncrementModel();

        model.initializeConfig("");
        
        assertEquals(1.0, model.getStartTime(), 0.0);
        
        model.setStartTime(5.0);
        
        assertEquals(5.0, model.getStartTime(), 0.0);
    }
    
    @Test(expected = BMIModelException.class)
    public void testSetStartTime_afterInit_Error() throws Exception {
        EBMI model = new IncrementModel();

        model.initializeConfig("");
        model.initializeState(null);
        
        model.setStartTime(5.0);
    }

    @Test
    public void testSetEndTime() throws Exception {
        EBMI model = new IncrementModel();

        model.initializeConfig("");
        
        assertEquals(20.0, model.getEndTime(), 0.0);
        
        model.setEndTime(10.0);
        
        assertEquals(10.0, model.getEndTime(), 0.0);
    }
    
    @Test(expected = BMIModelException.class)
    public void testSetEndTime_afterInit_Error() throws Exception {
        EBMI model = new IncrementModel();

        model.initializeConfig("");
        model.initializeState(null);
        
        model.setEndTime(5.0);
    }
    
    @Test
    public void testGetAttributeNames() throws Exception {
        assertArrayEquals("incorrect list of attribute names", new String[] { "author"}, model.getAttributeNames());
    }

    @Test
    public void testGetAttributeValue() throws Exception {
        assertEquals("wrong value for attribute", "Rolf Hut", model.getAttributeValue("author"));
    }
    
    @Test(expected = BMIModelException.class)
    public void testGetAttributeValue_InvalidAttribte_Exception() throws Exception {
        model.getAttributeValue("InvalidAttributeName");
    }

    @Test(expected = BMIModelException.class)
    public void testSetAttributeValue_Exception() throws Exception {
        model.setAttributeValue("some.attribute",  "some.value");
    }

    
    
    
    
    
    

}
