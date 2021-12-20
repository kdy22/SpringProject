package first.sample.controller;

import java.io.PrintWriter;
import java.util.Arrays;


import java.util.Iterator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import first.common.common.CommandMap;
import first.common.common.MemberVO;
import first.common.service.MemberService;
import first.sample.service.SampleService;

@Controller 
public class SampleController { 
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="sampleService") 
	private SampleService sampleService;
	
	
	
	//새로 추가된부분@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 여기서부터
	
	

	@Inject
	MemberService service;
	
	
	// 회원가입 get
	@RequestMapping(value = "/registers/register.do", method = RequestMethod.GET)
	public void getRegister() throws Exception {
		log.info("get register");
	}
	
	// 회원가입 post
	@RequestMapping(value = "/registers/register.do", method = RequestMethod.POST)
	public String postRegister(MemberVO vo, HttpServletRequest req) throws Exception {
		log.info("post register");
	
		System.out.println("vo 값은 이겁니다.22 : "+ vo);
		
		service.register(vo);
		
		HttpSession session = req.getSession();
		
		session.setAttribute("msg", "success");
		
		return "redirect:/index.jsp";
	
	}
	
	@RequestMapping(value ="/sample/login.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String login(Model model ,MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		log.info("post login");
	
		HttpSession session = req.getSession();
		MemberVO login = service.login(vo);
		
		System.out.println("vo 값은 이겁니다. : "+ vo);
		
		System.out.println("login값 :" + login);
		
		if(login == null) {
			session.setAttribute("member", null);
			//rttr.addFlashAttribute("msg", "fail");
			//model.addAttribute("msg","fail");
			session.setAttribute("msg","fail");
			System.out.println("들어오는지 체크합니다.");

		}else {
			session.setAttribute("member", login);
			session.setAttribute("msg","success");
		}
		
		
		/*
		String valeu = (String)session.getAttribute("member");
		System.out.println("member값이멀까요?? : " + valeu);
		*/
		/*
		String valeu2 = session.getAttribute("msg").toString();
		System.out.println("msg값이멀까요?? : " + valeu2);
		*/
		
		
		return "redirect:/index.jsp";
	}
	
	
	
	@RequestMapping(value ="/sample/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/index.jsp";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/sample/idChk.do", method = RequestMethod.POST)
	public int idChk(MemberVO vo) throws Exception {
		System.out.println("들어왓니? : ");
		int result = service.idChk(vo);
		return result;
	}
	


	
	
	
	@RequestMapping(value="/registers/memberUpdateView.do", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception{
		log.info("memberUpdateView.do들어옴");
		return "registers/memberUpdateView";
	}

	@RequestMapping(value="/registers/memberUpdate.do", method = RequestMethod.POST)
	public String registerUpdate(MemberVO vo, HttpSession session) throws Exception{
		
		service.memberUpdate(vo);
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	//새로 추가된부분@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 여기까지
	
	
	
	
	

	
	@RequestMapping(value="/sample/openBoardList.do") 
	public ModelAndView openBoardList(CommandMap commandMap) throws Exception{ 
		
		ModelAndView mv = new ModelAndView("/sample/boardList"); 
		/*
		System.out.println("commandMapppppppppppppp432143214321" + commandMap.getMap());
		List<Map<String,Object>> list = sampleService.selectBoardList(commandMap.getMap());
		System.out.println("commandMapppppppppppppp432143214321QQQQQQQQQ" + commandMap.getMap());
		mv.addObject("list", list);
		
		*/
		return mv; 
		
	}

	
	@RequestMapping(value="/sample/selectBoardList.do") 
	public ModelAndView selectBoardList(CommandMap commandMap) throws Exception{ 
		ModelAndView mv = new ModelAndView("jsonView"); 
		System.out.println("testtesttest1234" + commandMap.getMap());
		List<Map<String,Object>> list = sampleService.selectBoardList(commandMap.getMap()); 
		
		mv.addObject("list", list); 
		System.out.println("LIST@@@@@@@@@@@@@@@@@@@@@@" + list);
		if(list.size() > 0){ 
			mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT")); 
		} 
		else{ 
			mv.addObject("TOTAL", 0); 
		} 
		
		return mv; 
	}
	
	
	@RequestMapping(value="/sample/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardDetail");
		
		System.out.println("commandMapppppppppppppp1111111111" + commandMap.get("IDX").toString());
		System.out.println("commandMapppppppppppppp2222222222" + commandMap.getMap());
		
		
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());

		
		mv.addObject("map", map.get("map")); 
		mv.addObject("list", map.get("list"));

		return mv;
	}
	
	@RequestMapping(value="/sample/testMapArgumentResolver.do") 
	public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception{ 
		ModelAndView mv = new ModelAndView(""); 
		if(commandMap.isEmpty() == false){ 
			Iterator<Entry<String,Object>> iterator = commandMap.getMap().entrySet().iterator(); 
			Entry<String,Object> entry = null; 
			
			while(iterator.hasNext()){
				entry = iterator.next();
				log.debug("key : "+entry.getKey()+", value : "+entry.getValue()); 
			} 
		} 
		return mv; 
		
	}
	
	@RequestMapping(value="/sample/openBoardWrite.do") 
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{ 
		ModelAndView mv = new ModelAndView("/sample/boardWrite"); 
		System.out.println("commandMapppppppppppppp333333333" + commandMap.getMap());
		return mv; 
		
	}

	@RequestMapping(value="/sample/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{ 
		sampleService.insertBoard(commandMap.getMap(), request);
		System.out.println("확인합니다.~~~~~~ : " + commandMap.getMap());
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do"); 
		return mv; 
		
	}
	
	
	@RequestMapping(value="/sample/openBoardUpdate.do")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/sample/boardUpdate");
		
		Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
		
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/sample/updateBoard.do")
	public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail.do");
		
		sampleService.updateBoard(commandMap.getMap(), request);
		
		mv.addObject("IDX", commandMap.get("IDX"));
		return mv;
	}
	
	@RequestMapping(value="/sample/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
		
		sampleService.deleteBoard(commandMap.getMap());
		
		return mv;
	}
	
	
	
	
	
	
	
	
	// 회원 탈퇴 get
		@RequestMapping(value = "/registers/memberDeleteView.do", method = RequestMethod.GET)
		public String memberDeleteView() throws Exception{
			log.info("memberDeleteView get으로 들어왔어요!");
		
			return "registers/memberDeleteView";
		}
		
		// 회원 탈퇴 post
		@RequestMapping(value = "/registers/memberDeleteView.do", method = RequestMethod.POST)
		public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
			
			log.info("memberDeleteView post으로 들어왔어요!");
			
			// 세션에 있는 member를 가져와 member변수에 넣어줍니다.
			MemberVO member = (MemberVO) session.getAttribute("member");
			System.out.println("memberDelete함수의 세션에있는 member값이 먼지알아 ? 이거야 : " + member);
			System.out.println("memberDelete함수의 세션에있는 member값의 getuserpass값이 먼지알아 ? 이거야 : " + member.getUserPass());
			// 세션에있는 비밀번호
			String sessionPass = member.getUserPass();
			// vo로 들어오는 비밀번호
			String voPass = vo.getUserPass();
			System.out.println("memberDelete함수의 vo에있는 member값의 getuserpass값이 먼지알아 ? 이거야 : " + vo.getUserPass());
			if(!(sessionPass.equals(voPass))) {
				rttr.addFlashAttribute("delmsg", "fail");
				
				return "redirect:/registers/memberDeleteView.do";
			}
			service.memberDelete(vo);
			session.invalidate();
			return "redirect:/";
		}
	

}
