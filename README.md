# idempiere-java-webclient

This package is wrapper of [idempierewsc-java](https://github.com/sauljabin/idempierewsc-java).



## Maven

Get .jar from github.

```xml
<dependencies>
  <dependency>
    <groupId>com.github.asufana</groupId>
    <artifactId>idempiere-java-webclient</artifactId>
    <version>1.0</version>
  </dependency>
</dependencies>

<repositories>
    <repository>
        <id>asufana</id>
        <url>https://raw.github.com/asufana/idempiere-java-webclient/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
```

and you need to add a below library.

```xml
<dependency>
  <groupId>org.idempiere.webservice</groupId>
  <artifactId>idempierewsc-java</artifactId>
  <version>1.0</version>
  <scope>system</scope>
  <systemPath>${basedir}/exlib/idempierewsc.jar</systemPath>
</dependency>
```



## Sample Code

Connections.java

```java
import org.idempiere.webservice.client.base.LoginRequest;
import org.idempiere.webservice.client.net.WebServiceConnection;

public class Connections {
    private final static String logonId = "SuperUser";
    private final static String logonPw = "System";
    private final static Integer clientId = 11;
    private final static Integer orgId = 0;
    private final static Integer roleId = 102;
    private final static Integer stageId = 2;
    
    private final static String baseUrl = "http://192.168.56.42:8080";
    private final static Integer requestTimeout = 5000;
    private final static Integer numOfAttempts = 3;
    private final static Integer attemptTimeout = 5000;
    private final static String userAgentName = "idempiere-java-webclient";
    
    //login settings
    public static LoginRequest loginRequest() {
        final LoginRequest login = new LoginRequest();
        login.setUser(logonId);
        login.setPass(logonPw);
        login.setClientID(clientId);
        login.setOrgID(orgId);
        login.setRoleID(roleId);
        login.setStage(stageId);
        return login;
    }
    
    //connection settings
    public static WebServiceConnection webServiceConnection() {
        final WebServiceConnection client = new WebServiceConnection();
        client.setUrl(baseUrl);
        client.setTimeout(requestTimeout);
        client.setAttempts(numOfAttempts);
        client.setAttemptsTimeout(attemptTimeout);
        client.setAppName(userAgentName);
        return client;
    }   
}
```

BPartnerRepo.java

```java
import org.idempiere.webservice.client.base.DataRow;
import com.github.asufana.idempiere.webclient.factories.DataRowBuilder;
import com.github.asufana.idempiere.webclient.factories.RequestFactory;
import com.github.asufana.idempiere.webclient.functions.WSRequest;
import com.github.asufana.idempiere.webclient.functions.WSResponse;
import com.github.asufana.idempiere.webclient.types.RequestTable;
import lombok.NonNull;

public class BPartnerRepo {
    
    /** Create */
    public static WSResponse create(@NonNull final String name, @NonNull final String value) {
        final DataRow dataRow = new DataRowBuilder().add("Name", name)
                                                    .add("Value",
                                                         value + "_" + System.currentTimeMillis())
                                                    .add("TaxID", "-")
                                                    .build();
        final WSRequest request = RequestFactory.create(RequestTable.BPartner, dataRow);
        return request.sendRequest(Connections.loginRequest(), Connections.webServiceConnection());
    }
    
    /** Query */
    public static WSResponse findByName(@NonNull final String name) {
        final DataRow dataRow = new DataRowBuilder().add("Name", name).build();
        final WSRequest request = RequestFactory.query(RequestTable.BPartner, dataRow);
        return request.sendRequest(Connections.loginRequest(), Connections.webServiceConnection());
    }
    
    /* Read */
    public static WSResponse findById(@NonNull final Integer recordId) {
        final WSRequest request = RequestFactory.read(RequestTable.BPartner, recordId);
        return request.sendRequest(Connections.loginRequest(), Connections.webServiceConnection());
    }
    
    /** Update */
    public static WSResponse update(@NonNull final Integer recordId, @NonNull final String name) {
        final DataRow dataRow = new DataRowBuilder().add("Name", name).build();
        final WSRequest request = RequestFactory.update(RequestTable.BPartner, recordId, dataRow);
        return request.sendRequest(Connections.loginRequest(), Connections.webServiceConnection());
    }
    
    /** Delete */
    public static WSResponse delete(@NonNull final Integer recordId) {
        final WSRequest request = RequestFactory.delete(RequestTable.BPartner, recordId);
        return request.sendRequest(Connections.loginRequest(), Connections.webServiceConnection());
    }   
}
```

BPartnerRepoTest.java

```java
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import com.github.asufana.idempiere.webclient.functions.WSResponse;

public class BPartnerRepoTest {
    
    @Test
    //Create
    public void testCreate() throws Exception {
        final WSResponse response = create();
        
        System.out.println(response);
    }
    
    private WSResponse create() {
        final String name = "WebClientTest:" + System.currentTimeMillis();
        final WSResponse response = BPartnerRepo.create(name, name);
        
        assertThat(response.isSuccess(), is(true));
        assertThat(response.size(), is(not(0)));
        return response;
    }
    
    @Test
    //Query
    public void testFindByName() throws Exception {
        final WSResponse response = BPartnerRepo.findByName("GardenAdmin BP");
        
        assertThat(response.isSuccess(), is(true));
        assertThat(response.size(), is(not(0)));
        System.out.println(response);
    }
    
    @Test
    //Read
    public void testFindById() throws Exception {
        final WSResponse response = BPartnerRepo.findById(113); //GardenAdmin BP
        
        assertThat(response.isSuccess(), is(true));
        assertThat(response.size(), is(not(0)));
        System.out.println(response);
    }
    
    @Test
    //Update
    public void testUpdate() throws Exception {
        //Create
        final WSResponse createResponse = create();
        
        //Update
        final WSResponse response = BPartnerRepo.update(createResponse.recordId(),
                                                        "WebClientTestUpdated:"
                                                                + System.currentTimeMillis());
        
        assertThat(response.isSuccess(), is(true));
        assertThat(response.size(), is(not(0)));
        System.out.println(response);
    }
    
    @Test
    //Delete
    public void testDelete() throws Exception {
        //Create
        final WSResponse createResponse = create();
        
        //Delete
        final WSResponse response = BPartnerRepo.delete(createResponse.recordId());
        
        assertThat(response.isSuccess(), is(true));
        assertThat(response.size(), is(not(0)));
        System.out.println(response);
    }   
}
```



## iDempiere WebServiceType Setting

You use 2pack to copy WebServiceType. 

-   `sample/C_BPartner_2Pack.zip` 

