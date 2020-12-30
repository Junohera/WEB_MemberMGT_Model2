package com.juno.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juno.controller.action.Action;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
			※ 모든 명령(command)의 입력 창구 : member.do
			※ 모든 명령(command)의 실행 주체 : Action 인터페이스
			※ 모든 명령(command)의 실제 명령문들 : 각 기능별 클래스의 execute메서드
			인터페이스는 하나, 기능별 클래스는 여러개
			해당 명령에 따른 클래스의 인스턴스를 인터페이스에 할당하고 execute 명령을 실행합니다.
			
			
			1. 현재 프로젝트에 Action이라는 인터페이스를 제작합니다.
				- com.juno.controller.action.Action.java;
				
			2. 더불어 각 command에 따른 명령을 담은 java class를 제작합니다.
				이는 Action인터페이스에 execute라는 추상메서드가 있고, 제작되는 각 클래스는
				그 기능을 오버라이드하는 execute메서드에 모든 실행명령을 기술합니다.
				- com.juno.controller.action.Login_Form_Action.java
				
			3. 각 클래스는 모두 Action인터페이스를 상속(구현)하여 제작합니다.
				- @Override execute
			
			4. 현재의 doGet에서는 전달된 command의 value에 따라 해당 기능을 갖고 있는
				클래스를 Action인터페이스의 레퍼런스변수에 할당합니다.(부모레퍼런스 <- 자식인스턴스)
				
				- 인터페이스 레퍼런스에 클래스 인스턴스를 넣고, 인터페이스 레퍼런스로 메서드 실행
				
					1.
						Action ac = null;
						Login_Form_Action lfa = new Login_Form_Action();
						ac = lfa;
						ac.execute();
						
					2.
						Action ac = new Login_Form_Action();
						ac.execute();
						
					3.
						ac = new Login_Form_Action();
						ac.execute();
				
			5. 그리고 Action에서 오버라이드된 execute 메서드를 실행합니다.
				Action ac = null;
				
				if (command.equals("login_form")) ac = new Login_Form_Action();
				else if (command.equals("a")) ac = new aAction();
				else if (command.equals("b")) ac = new bAction();
				else if (command.equals("c")) ac = new cAction();
				else if (command.equals("d")) ac = new dAction();
				
				ac.execute();
			
			6. 각 클래스와 인터페이스의 할당(조립)은 별도의 클래스(ActionFactory)에 기능을 일임합니다.
			
				- 결국 이곳에 필요한 소스들은 다음과 같고,
					String command = request.getParameter("command");
					Action ac = null;
					ActionFactory af = ActionFactory.getIst();
					Action ac = af.getAction(command);
					ac.execute(request, response);
				
				- 더 축약할 경우
					ActionFactory af = ActionFactory.getIst(); // singletone
					Action ac = af.getAction(request.getParameter("command")); // command에 따른 객체생성은 af.getAction에 일임
					ac.execute(request, response);
					
				- result
					ActionFactory.getIst() // 객체 반환
						.getAction(request.getParameter("command")) // 상황(command)마다 분기를 통해 특정 역할을 가진 인스턴스 반환
						.execute(request, response); // 실행
		*/
		
		ActionFactory.getIst()
			.getAction(request.getParameter("command"))
			.execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
