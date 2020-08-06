const express = require("express");
const app = express();
const bodyParser = require("body-parser");
const port = 3000; //porta padrão
const sql = require("mssql");
const connStr =
  "Server=regulus;Database=BD19351;User Id=BD19351;Password=euamofiana;";

sql
  .connect(connStr)
  .then(conn => (global.conn = conn))
  .catch(err => console.log(err));

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

const router = express.Router();
router.get("/", (req, res) => res.json({ message: "Funcionando!" }));
app.use("/", router);

function execSQLQuery(sqlQry, res) {
  global.conn
    .request()
    .query(sqlQry)
    .then(result => res.json(result.recordset))
    .catch(err => res.json(err));
}

router.get("/clientes", (req, res) => {
  execSQLQuery("SELECT * FROM OiTeste", res);
});

router.get("/clientes/:id?", (req, res) => {
  let filter = "";
  if (req.params.id) filter = " WHERE ID=" + parseInt(req.params.id);
  execSQLQuery("SELECT * FROM OiTeste" + filter, res);
});

router.delete("/clientes/:id", (req, res) => {
  execSQLQuery("DELETE OiTeste WHERE ID=" + parseInt(req.params.id), res);
});

app.listen(port);
console.log("API funcionando!");
