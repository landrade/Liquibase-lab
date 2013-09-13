package br.com.landrade.tests;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import br.com.landrade.configurations.PersistenceConfig;
import br.com.landrade.tests.utils.DatabaseStructUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)
public class CreateSimpleTableTest {

	@Inject
	private DataSource dataSource;

	@Test
	public void shouldExistsTablePerson() throws SQLException {
		String tableName = "PESSOA";
		Connection con = dataSource.getConnection();
		DatabaseStructUtils.assertExistTable(con, tableName);
	}

}
