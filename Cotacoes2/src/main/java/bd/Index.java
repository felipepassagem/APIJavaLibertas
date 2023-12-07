package bd;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import com.google.gson.Gson;




/**
 */
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        CotacaoDao cdao = new CotacaoDao();
        List<Cotacao> lista = cdao.listar();
        Gson gson = new Gson();
        out.print(gson.toJson(lista));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");
		PrintWriter out = response.getWriter();
		//out.println("Executando método POST");
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine())  != null) {
				sb.append(line);
			}
			String body = sb.toString();
			Gson gson = new Gson();
			Cotacao c = gson.fromJson(body, Cotacao.class);
			
			CotacaoDao cdao = new CotacaoDao();
			cdao.inserir(c);
			Response r = new Response(200, "Cotacao inserida com sucesso.");
			out.print(gson.toJson(r));
		} catch (Exception e) {
			e.printStackTrace();
			Gson gson = new Gson();
			Response r = new Response(501, "Erro ao inserir cotação");
			out.print(gson.toJson(r));
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");
		PrintWriter out = response.getWriter();
		//out.println("Executando método POST");
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine())  != null) {
				sb.append(line);
			}
			String body = sb.toString();
			Gson gson = new Gson();
			Cotacao c = gson.fromJson(body, Cotacao.class);
			
			CotacaoDao pdao = new CotacaoDao();
			pdao.alterar(c);
			Response r = new Response(200, "Cotacao alterada com sucesso.");
			out.print(gson.toJson(r));
		} catch (Exception e) {
			e.printStackTrace();
			Gson gson = new Gson();

			Response r = new Response(501, "Erro ao alterar cotação");
			out.print(gson.toJson(r));
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");
		PrintWriter out = response.getWriter();
		try {
//			String id = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
//			Gson gson = new Gson();
//
			CotacaoDao cdao = new CotacaoDao();
//			c.setId(Integer.parseInt(id));
//			System.out.println(id);
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine())  != null) {
				sb.append(line);
			}
			String body = sb.toString();
			Gson gson = new Gson();
			Cotacao c = gson.fromJson(body, Cotacao.class);
			cdao.excluir(c);
			Response r = new Response(200, "Cotacao removida com sucesso.");
			out.print(gson.toJson(r));
		} catch (Exception e) {
			// TODO: handle exception
			Gson gson = new Gson();

			Response r = new Response(501, "Erro ao deletar cotação");
			out.print(gson.toJson(r));
		}
	}

}
