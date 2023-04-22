package com.example.demo.services;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import com.example.demo.models.DoCommitRequest;
import oracle.jdbc.OracleTypes;

/**
 * @author YoussefMahmoud
 * @created Apr 22, 2023-5:08:44 PM
 */

@Service
public class DoCommitService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static final String CONFIRM_DB = "CONFIRM_DB";
	public static final String DO_COMMIT_C = "DO_COMMIT_C";
	public static final String RETURN = "RETURN";
	public static final String P_PAGE_ID = "P_PAGE_ID";
	public static final String P_USRNO = "P_USRNO";
	public static final String P_OPRNO = "P_OPRNO";
	public static final String P_FUNC_MODE = "P_FUNC_MODE";
	public static final String P_JSON_REQUEST = "P_JSON_REQUEST";

	private BigDecimal doCommitImpl(Integer pageId, Integer userNumber, Integer operationNumber, String functionMode,
			String jsonObject, JdbcTemplate jdbcTemplate) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(CONFIRM_DB)
				.withFunctionName(DO_COMMIT_C).withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlOutParameter(RETURN, OracleTypes.NUMBER),
						new SqlParameter(P_PAGE_ID, OracleTypes.NUMBER), new SqlParameter(P_USRNO, OracleTypes.NUMBER),
						new SqlParameter(P_OPRNO, OracleTypes.NUMBER),
						new SqlParameter(P_FUNC_MODE, OracleTypes.VARCHAR),
						new SqlParameter(P_JSON_REQUEST, OracleTypes.CLOB));
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue(P_PAGE_ID, pageId)
				.addValue(P_USRNO, userNumber).addValue(P_OPRNO, operationNumber).addValue(P_FUNC_MODE, functionMode)
				.addValue(P_JSON_REQUEST, jsonObject);
		return simpleJdbcCall.executeFunction(BigDecimal.class, paramMap);
	}

	public BigDecimal doCommit(DoCommitRequest doCommitRequest) {
		return doCommitImpl(doCommitRequest.getPageId(), doCommitRequest.getUserNumber(),
				doCommitRequest.getOperationNumber(), doCommitRequest.getFunctionMode(),
				doCommitRequest.getFormFieldsData(), jdbcTemplate);
	}

}