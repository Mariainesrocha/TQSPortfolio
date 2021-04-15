# Lab4 ex1 questions solution

### Q1. Identify a couple of examples on the use of AssertJ expressive methods chaining.
        - A:  
                * EmployeeRepositoryTest.Java:
                **(l. 27)** assertThat( found ).isEqualTo(alex);
                **(l. 42)** assertThat(fromDb).isNotNull();
                **(l. 43)** assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail());
                **(l. 49; l.33)** assertThat(fromDb).isNull();
                **(l. 65)** assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());


                * EmployeeRestControllerIT.Java:
                **(l. 51)** assertThat(found).extracting(Employee::getName).containsOnly("bob");


                * EmployeeService_UnitTest.Java:
                **(l. 53)** assertThat(found.getName()).isEqualTo(name);
                **(l. 59)** assertThat(fromDb).isNull();
                **(l. 67)** assertThat(doesEmployeeExist).isEqualTo(true);
                **(l. 75)** assertThat(doesEmployeeExist).isEqualTo(false);
                **(l. 83)** assertThat(fromDb.getName()).isEqualTo("john");
                **(l. 92)** assertThat(fromDb).isNull();
                **(l. 103)** assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());


                * EmployeeRestControllerTemplateIT.Java:
                **(l. 55)** assertThat(found).extracting(Employee::getName).containsOnly("bob");
                **(l. 71)** assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
                **(l. 72)** assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");


        --------------------------------------------------------------


### Q2. Identify an example in which you mock the behavior of the repository (and avoid involving a database).

        - A:  In the file **EmployeeService_UnitTest.java** is where we have an example of the repository behavior mocked.


        --------------------------------------------------------------


### Q3. What is the difference between standard @Mock and @MockBean?

        - A: The @Mock annotation is provided by **Mockito framework** while @MockBean is a **Spring Boot** annotation that's only used inside Spring framework, whenever we install the spring-boot-test dependency.
        
        The first one is used to mock some classes or interfaces, which means it indicates to Mockito that the class annotated its not completly implemented and its necessary to create and instanciate a mock (something like a generic and simpler version of that class) to test some other classes that depend on her.

        The second one is used when we have a spring project with components like services, respositories and others. These kind of components are also called beans and because the spring boot context has a special mecanism to instanciate this components the mock needed to them is a litle bit different from the first one. Consequently, the annotation @MockBean implies some extra spring configurations to the mocks of that bean components. 

        --------------------------------------------------------------


### Q4. What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

        - A: This file is similar to the application.properties file that is created in the resources folder inside the main folder of the spring project and has the same role but directed to the run of test classes. 
        Inside some test classes the tag @TestPropertySource allows the spring to detect the application-integrationtest.properties location and with the configurations define inside of it its possible to create some connections with external tools. In some cases, these external tools improve the quality of our tests because they provide a more realist aproach to the deployment enviroment of our applucation. As example of some tools that can be configure in this file, we have, in excersice 3 of this guide, a real database connection between the class that is been tested and a mysql container running in a docker enviroment.