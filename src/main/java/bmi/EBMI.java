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
     * Optional two-phase initialization function. First this function is called to read all configuration data,
     * then the initializeState() function can be used to fully initialize the model. In between metadata functions such as
     * setAttribute and setStartTime can be used to configure the model. Availability of these functions highly
     * depend on the model
     * @param configFile the location of the configuration file
     * @throws BMIModelException in case of problems
     */
    void initializeConfig(String configFile) throws BMIModelException;
    
    void initializeState(String sourceDirectory) throws BMIModelException;

    //usually only possible before initializeState
    void setStartTime(double startTime) throws BMIModelException;

    //usually only possible before initializeState
    void setEndTime(double endTime) throws BMIModelException;

    public String[] getAttributeNames() throws BMIModelException;

    String getAttributeValue(String attributeName) throws BMIModelException;

    String setAttributeValue(String attributeName, String attributeValue) throws BMIModelException;

    void saveState(String destinationDirectory) throws BMIModelException;

}
