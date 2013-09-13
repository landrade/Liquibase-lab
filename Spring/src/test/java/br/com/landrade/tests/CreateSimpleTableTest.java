package br.com.landrade.tests;

import static junit.framework.Assert.fail;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import br.com.landrade.configurations.PersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class }, loader = AnnotationConfigContextLoader.class)
public class CreateSimpleTableTest {

	@Inject
	private DataSource dataSource;

	@Test
	public void deveCriarUmaTabelaComSucessoAoSubirOSpring() throws SQLException {
		String tableName = "PESSOA";

		final ResultCollector rc = new ResultCollector();

		Connection con = dataSource.getConnection();
		DatabaseMetaData dbmd = con.getMetaData();
		ResultSet tables = dbmd.getTables(null, null, tableName.toUpperCase(), null);
		while (tables.next()) {
			if (tables.getString(3).toUpperCase().equals(tableName.toUpperCase())) {
				rc.found = true;
			}
		}

		if (!rc.found) {
			fail("Table not found in schema : " + tableName);
		}
	}

	static class ResultCollector {
		public boolean found = false;
	}

}
