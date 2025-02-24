<%@page import="com.mentevida.nucleo.Medico"%>
<%@page import="com.mentevida.nucleo.Paciente"%>
<%@page import="com.mentevida.dao.PacienteDAO"%>
<%@page import="com.mentevida.dao.MedicoDAO"%>
<%@page import="com.mentevida.nucleo.Consulta"%>
<%@page import="java.util.List"%>
<%@page import="com.mentevida.dao.ConsultaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Gerenciamento de Funcionários da Clínica Mente & Vida.">
        <title>Consultas - Clínica Mente & Vida</title>
        <link rel="stylesheet" href="css/relatorio.css">
    </head>
    <body>
         <header class="topo">
        <img src="img/psc.png" alt="Logo da Clínica" class="clinica-imagem">
        <div>
            <h1>Clínica Mente & Vida</h1>
            <p>Saúde Mental e Bem-Estar</p>
        </div>
        <nav class="menu">
            <ul class="nav-list">
                <li onclick="location = 'home.jsp'">Início</li>
                <li onclick="location = 'paciente.jsp'">Pacientes</li>
                <li onclick="location = 'medico.jsp'">Médicos</li>
                <li onclick="location = 'funcionario.jsp'">Funcionários</li>
                <li onclick="location = 'agendamento.jsp'">Agendamentos</li>
                <li onclick="location = 'consulta.jsp'">Consultas</li>
                <li onclick="location = 'prescricao.jsp'">Prescrições</li>
                <li onclick="location = 'relatorio.jsp'">Relatórios</li>
                <li onclick="location = 'perfil.jsp'">Perfil</li>
            </ul>
        </nav>
    </header>

        <%
            int idConsulta = 0;
            int duracao = 0;
            double valor = 0;
            String nomePaciente = "";
            String nomeMedico = "";
            int idPaciente = 0;
            int idMedico = 0;
        %>

        <main>
            <h2>Consultas Registrados</h2>

            <p><a href="gerenciaConsulta.jsp" class="novo-relatorio">Nova Consulta</a></p>


            <table width="100%" border="1">
                <tr>
                    <th>ID</th>
                    <th>Duração</th>
                    <th>Valor</th>
                    <th>Paciente</th>
                    <th>Médico</th>
                    <th>Ações</th>
                </tr>
                <%
                    ConsultaDAO dao = new ConsultaDAO();
                    List<Consulta> listaConsulta = dao.mostrarTodasConsultas();
                    MedicoDAO med = new MedicoDAO();
                    PacienteDAO pac = new PacienteDAO();
                    Paciente paciente = new Paciente();
                    Medico medico = new Medico();

                    
                    Consulta consulta = null;
                    
                    int i = 0;
                    while (i < listaConsulta.size()) {
                        consulta = listaConsulta.get(i);
                        
                        idPaciente = consulta.getPaciente().getIdPaciente();
                        idMedico = consulta.getMedico().getIdMedico();
                        
                        if (pac.mostrarIdPacientes(idPaciente).get(0) != null) {
                            paciente = pac.mostrarIdPacientes(idPaciente).get(0);
                        }
                        if (med.mostrarIdMedicos(idMedico).get(0) != null) {
                            medico = med.mostrarIdMedicos(idMedico).get(0);
                        }

                        idConsulta = consulta.getIdConsulta();
                        duracao = consulta.getDuracao();
                        valor = consulta.getValor();
                        nomePaciente = "<spam title='ID do paciente é " + idPaciente + "'>" + paciente.getNome() + "</spam>";
                        nomeMedico = "<spam title='ID do médico é " + idMedico + "'>" + medico.getNome() + "</spam>";
                %>
                <tr>
                    <td>
                        <%=idConsulta%>
                    </td>
                    <td>
                        <%=duracao%>
                    </td>
                    <td>
                        <%=valor%>
                    </td>
                    <td>
                        <%=nomePaciente%>
                    </td>
                    <td>
                        <%=nomeMedico%>
                    </td>
                    <td>
                        <form class="botoesAcao" action="gerenciaConsulta.jsp" method="POST">
                            <button class="btn-editar">
                                Editar
                            </button>
                            <input type="hidden" name="idConsulta" value="<%=idConsulta%>">
                        </form>

                        <form class="botoesAcao" action="ControleConsulta" method="POST" onsubmit="return confirm('Você tem certeza de que quer excluir o médico <%=idConsulta%>?');">
                            <input type="hidden" name="idConsulta" value="<%=idConsulta%>">
                            <input type="hidden" name="excluir" value="true">
                            <button class="btn-excluir">
                                Excluir
                            </button>
                        </form>
                    </td>
                </tr>
                <%
                        i++;
                    }
                %>

            </table>
                
        </main>

    </body>
</html>
