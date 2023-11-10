package org.example.DAO;


import org.example.DocumentDTO;
import org.example.DocumentEntity;
import org.example.DocumentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**@Component
public class DocumentDAOJDBCTemplate implements DocumentDAO{
    private JdbcTemplate template;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String SQL_INSERT = "insert into document(id, documentType, organization, date, description, patient, status) values (:id, :documentType, :organization,:date, :description, :patient, :status)";
    private final String SQL_SELECT_DOCUMENTS = "select * from document";
    @Autowired
    public DocumentDAOJDBCTemplate(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void createRecord(DocumentDTO document){
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", document.getId());
        params.put("documentType", document.getDocumentType());
        params.put("organization", document.getOrganization());
        params.put("date", document.getDate());
        params.put("description", document.getDescription());
        params.put("patient", document.getPatient());
        params.put("status", document.getStatus());
        namedParameterJdbcTemplate.update(SQL_INSERT, params);
    }

    @Override
    public List<DocumentDTO> getRecords() {
        return template.query(SQL_SELECT_DOCUMENTS, documentRowMapper);
    }
    private RowMapper<DocumentDTO> documentRowMapper = (ResultSet rs, int i) ->
            DocumentDTO.builder()
                    .id(rs.getInt("id"))
                    .documentType(rs.getString("documentType"))
                    .organization(rs.getString("organization"))
                    .date(rs.getString("date"))
                    .description(rs.getString("description"))
                    .patient(rs.getString("patient"))
                    .status(rs.getString("status")).build();
}**/
