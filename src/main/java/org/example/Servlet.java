package org.example;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.Service.DocumentService;
import org.example.Service.DocumentServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/list")
public class Servlet extends HttpServlet {
    private static final DocumentService documentService = new DocumentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DocumentDTO documentDTO = documentService.createRecord(DocumentDTO.builder().documentType("����������")
                .patient("�����").organization("�������� 4").description("����������").build());
        DocumentDTO documentDTO1 = documentService.createRecord(DocumentDTO.builder().documentType("�����")
                .patient("�������").organization("�������� 5").description("�����").build());
        List<DocumentDTO> documents = documentService.getRecords();
        request.setAttribute("documents", documents);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);

    }
}