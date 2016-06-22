import org.scalajs.dom
import scala.scalajs.js


object Main extends js.JSApp{
  override def main(): Unit = {
    dom.document.getElementById("scalajsShoutOut").textContent = Model.itWorks
  }
}
