/*
* Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* WSO2 Inc. licenses this file to you under the Apache License,
* Version 2.0 (the "License"); you may not use this file except
* in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.wso2.das.integration.common.clients;

import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.analytics.messageconsole.stub.MessageConsoleMessageConsoleExceptionException;
import org.wso2.carbon.analytics.messageconsole.stub.MessageConsoleStub;
import org.wso2.carbon.analytics.messageconsole.stub.beans.TableBean;

import java.rmi.RemoteException;

public class MessageConsoleClient {

    private static final Log log = LogFactory.getLog(MessageConsoleClient.class);
    private final String serviceName = "MessageConsole";
    private MessageConsoleStub messageConsoleStub;

    public MessageConsoleClient(String backEndUrl, String sessionCookie) throws AxisFault {
        String endPoint = backEndUrl + serviceName;
        try {
            messageConsoleStub = new MessageConsoleStub(endPoint);
            AuthenticateStubUtil.authenticateStub(sessionCookie, messageConsoleStub);
        } catch (AxisFault axisFault) {
            log.error("MessageConsoleStub Initialization fail " + axisFault.getMessage());
            throw new AxisFault("MessageConsoleStub Initialization fail " + axisFault.getMessage());
        }
    }

    public MessageConsoleClient(String backEndUrl, String userName, String password)
            throws AxisFault {
        String endPoint = backEndUrl + serviceName;
        try {
            messageConsoleStub = new MessageConsoleStub(endPoint);
            AuthenticateStubUtil.authenticateStub(userName, password, messageConsoleStub);
        } catch (AxisFault axisFault) {
            log.error("MessageConsoleStub Initialization fail " + axisFault.getMessage());
            throw new AxisFault("MessageConsoleStub Initialization fail " + axisFault.getMessage());
        }
    }

    public String[] listTables() throws MessageConsoleMessageConsoleExceptionException, RemoteException {
        return messageConsoleStub.listTables();
    }

    public void createTable(TableBean tableBean)
            throws MessageConsoleMessageConsoleExceptionException, RemoteException {
        messageConsoleStub.createTable(tableBean);
    }

    public TableBean getTableInfo(String tableName)
            throws MessageConsoleMessageConsoleExceptionException, RemoteException {
        return messageConsoleStub.getTableInfo(tableName);
    }

    public void editTable(TableBean tableBean)
            throws MessageConsoleMessageConsoleExceptionException, RemoteException {
        messageConsoleStub.editTable(tableBean);
    }
}
