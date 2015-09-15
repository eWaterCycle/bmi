/*
 * Copyright 2015 Netherlands eScience Center
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
 * Extensions of the BMI interface as used by eWaterCycle and OpenDA.
 * 
 * @author Niels Drost
 *
 */
public interface EBMI extends BMI {

    /**
     * Optional two-phase initialization function. First this function is called to read all configuration data, then the
     * initializeModel() function can be used to fully initialize the model. In between metadata functions such as setAttribute
     * and setStartTime can be used to configure the model. Availability of these functions highly depend on the model
     * 
     * @param configFile
     *            the location of the configuration file
     * @throws BMIModelException
     *             in case of problems
     */
    void initializeConfig(String configFile) throws BMIModelException;

    /**
     * Second step of two-phase initialize. In this step the model is setup, and will now allow reading/setting values.
     * 
     * @throws BMIModelException
     */
    void initializeModel() throws BMIModelException;

    /**
     * Set the start time of the model. Can usually only be called after initialize_config and before initialize_model.
     * 
     * @param startTime
     *            a start time value in the time units of the model.
     * @throws BMIModelException
     */
    void setStartTime(double startTime) throws BMIModelException;

    /**
     * Set the end time of the model. Can usually only be called after initializeConfig and before initializeModel.
     * 
     * @param endTime
     *            a end time value in the time units of the model.
     * @throws BMIModelException
     */
    void setEndTime(double endTime) throws BMIModelException;

    /**
     * Gets a list of all supported attributes for this model. Attributes can be considered the meta-data of a model, for instance
     * author, version, model specific settings, etc.
     * 
     * @return a list of all supported attributes for this model.
     * @throws BMIModelException
     *             in case of problems
     */
    public String[] getAttributeNames() throws BMIModelException;

    /**
     * Gets the value of a certain attribute for this model. Attributes can be considered the meta-data of a model, for instance
     * author, version, model specific settings, etc.
     * 
     * @param attributeName
     *            the attribute to get the value for
     * @return the value of the given attribute
     * @throws BMIModelException
     *             in case of problems
     */
    String getAttributeValue(String attributeName) throws BMIModelException;

    /**
     * Sets the value of a certain attribute for this model. Usually only string values are allowed. Attributes can be considered
     * the meta-data of a model, for instance author, version, model specific settings, etc.
     * 
     * @param attributeName the name of the attribute to set
     * @param attributeValue the value to set the attribute to
     * @return
     * @throws BMIModelException
     */
    void setAttributeValue(String attributeName, String attributeValue) throws BMIModelException;

    /**
     * Ask the model to write its complete internal current state to one or more state files in the given directory.
     * Afterwards the given directory should only contain the state files and nothing else.
     * 
     * @param destinationDirectory the directory in which the state files should be written.
     * @throws BMIModelException in case of problems
     */
    void saveState(String destinationDirectory) throws BMIModelException;

    /**
     * Ask the model to load its complete internal current state from one or more state files in the given directory.
     * 
     * @param sourceDirectory the directory from which the state files should be read.
     * 
     * @throws BMIModelException
     */
    void loadState(String sourceDirectory) throws BMIModelException;
}
