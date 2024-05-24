package jp.te4a.spring.boot.myapp7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class HelloController {//HTTPアクセス(URL)の対応を記述
    @Autowired
    BookService bookService;
    @RequestMapping("books/list")
    public String index(Model model){
        model.addAttribute("msg","this is setting message");
        return "books/list";
    }

    @RequestMapping(value="books/list",method=RequestMethod.POST)
    public ModelAndView postForm(@RequestParam("id")String id,
        @RequestParam("title")String title,
        @RequestParam("writer")String writer,
        @RequestParam("publisher")String publisher,
        @RequestParam("price")String price){
            ModelAndView mv = new ModelAndView("books/list");
            bookService.save(new BookBean(Integer.valueOf(id),title,writer,publisher,Integer.valueOf(price)));
            StringBuffer buff = new StringBuffer();
            for(BookBean bean:bookService.findAll()){
                buff.append("ID"+bean.getId()+"<BR>"+"タイトル"+bean.getTitle()+"<BR>"
                +"著者"+bean.getWriter()+"<BR>"+"出版社"+bean.getPublisher()+"<BR>"+"価格"+bean.getPrice()+"<BR><HR>");
            }
            mv.addObject("books", bookService.findAll());
            return mv;
        }
}
