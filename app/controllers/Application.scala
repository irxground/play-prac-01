package controllers

import play.api._
import play.api.mvc._
import javax.script._


object Application extends Controller {
  val rubycode = """

def fib(n)
  if n <= 1
    1
  else
    fib(n-1) + fib(n-2)
  end
end

"fib 10 = #{fib(10)}"
  """

  def index = Action {
    val m = new ScriptEngineManager()
    val e = m.getEngineByName("jruby")
    val c = e.getContext()

    var hello = e.eval(rubycode, c)

    val txt = "Your new application is ready." + hello
    Ok(views.html.index(txt))
  }
}
