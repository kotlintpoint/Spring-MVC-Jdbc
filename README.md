# Spring-MVC-Jdbc
Spring MVC with JDBC Step By Step Solution

###### Step 1: Create Web Dynamic Web Project

###### Step 2: Add all Spring webmvc mysql library in your project's WebContent/WEB-INF/lib folder

###### Step 3: Create servlet and servlet-mapping inside web.xml for spring dispatcher as below -
```
<servlet>
  	<servlet-name>spring-dispatcher</servlet-name>
  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  </servlet>
  <servlet-mapping>
  	<servlet-name>spring-dispatcher</servlet-name>
  	<url-pattern>/</url-pattern>
  </servlet-mapping>
```

###### Step 4: Create spring-dispatcher-servlet.xml bean configuration file inside WebContent/WEB-INF/ folder and create beans as below - 
```
<context:component-scan base-package="com.controller" />

<bean id="viewResolver" 
	class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<property name="prefix">
		<value>/WEB-INF/view/</value>
	</property>
	<property name="suffix">
		<value>.jsp</value>
	</property>	
</bean>

<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
		<property name="url" value="jdbc:mysql://localhost:3306/db1"></property>
		<property name="username" value="root"></property>
		<property name="password" value=""></property>
</bean>

<bean id="dao" class="com.controller.SimpleJdbcDao" autowire="byName"></bean>
```
###### Step 5: Create package com.controller inside src folder and then create HelloController inside that package as below -
```
@Controller
public class HelloController{
	
	@Autowired
	private SimpleJdbcDao dao;
	
	
	public SimpleJdbcDao getDao() {
		return dao;
	}

	public void setDao(SimpleJdbcDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/")
	protected ModelAndView index() {
		// TODO Auto-generated method stub
		ModelAndView modelAndView=new ModelAndView("display");
		List<User> users=getDao().getUsers();
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
	@RequestMapping("insert")
	protected String insertForm() {
		return "insert";
	}
	
	@RequestMapping("submit")
	public String submit(HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParameter("name");
		String mobile=request.getParameter("mobile");
		int count=getDao().insert(name, mobile);
		if(count>0)
			return "redirect:display";
		else 
			return "insert";
	}
	
	@RequestMapping("display")
	public ModelAndView display() {
		ModelAndView modelAndView=new ModelAndView("display");
		List<User> users=getDao().getUsers();
		modelAndView.addObject("users", users);
		return modelAndView;
	}
}
```
###### Step 6: Create SimpleJdbcDao class for insert and fetch data as below - 
```
public class SimpleJdbcDao extends JdbcDaoSupport{
	public int insert(String name, String mobile) {
		String sql="insert into user (name, mobile) values (?,?)";
		return getJdbcTemplate().update(sql, name,mobile);		
	}
	
	public List<User> getUsers() {
		String sql="select * from user";
		List<User> users=getJdbcTemplate().query(sql, new UserRowMapper());	
		return users;
	}
	
	class UserRowMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int pos) throws SQLException {
			// TODO Auto-generated method stub
			User user=new User();
			user.setId(rs.getInt(1));
			user.setName(rs.getString(3));
			user.setMobile(rs.getString(2));
			return user;
		}
		
	}
}
```
###### Step 7: We can create User model class as below - 
```
public class User {
	private int id;
	private String name,mobile,email;

	// getter and setter 
}
```
###### Step 8: Now we can create display.jsp page inside WebContent/WEB-INF/view/ folder and write below code inside body tag
```
<a href="insert">New Entry</a><br>
	<c:forEach var="item" items="${users}">
    <td>
       ${item.getId()}
    </td>
    <td>
       ${item.getName()}
    </td>
    <td>
       ${item.getMobile()}
    </td>
    <td>Edit</td>
    <td>Delete</td>
</c:forEach>
```
###### Step 9: Create insert.jsp page inside  WebContent/WEB-INF/view/ folder and write below code inside body tag 
```
<form action="submit" method="post">
	Name : <input type="text" name="name"/><br>
	Mobile : <input type="text" name="mobile"><br>
	<input type="submit" value="Submit">
</form>
```
