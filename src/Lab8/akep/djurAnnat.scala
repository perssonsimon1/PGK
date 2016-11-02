package Lab8.akep

/**
  * Created by simonpersson on 2016-11-02.
  */
class djur_annat (
                   slakteri: String,
                   slaktdat: String,
                   slaktid: String,
                   djurslag: Int,
                   posttyp: String,
                   vikt: Double,
                   klass: String,
                   fettgr: String,
                   vetanm: String,
                   orgnr: String,
                   addid: String,
                   djurlev: String
                 ){

  def weight_zf(): String = {
    "%07d".format((vikt * 10).toInt)
  }

  def toTXTFormat(): String = {
    var out:String = ""
    out += slakteri.padTo(4,' ')
    out += slaktdat
    out += slaktid.padTo(10,' ')
    out += djurslag
    out += posttyp
    out += weight_zf
    out += klass.padTo(3,' ')
    out += fettgr.padTo(3,' ')
    out += vetanm.padTo(15, ' ')
    out += orgnr
    out += addid.padTo(2,' ')
    out += djurlev.padTo(6,' ')
    out
  }

}
