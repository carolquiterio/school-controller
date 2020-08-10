const express = require("express");
const sql = require("mssql");
const bodyParser = require("body-parser");

const port = 3000;

const connStr =
  "Server=regulus;Database=BD19351;User Id=BD19351;Password=euamofiana;";

const app = express();

sql
  .connect(connStr)
  .then(conn => (global.conn = conn))
  .catch(err => console.log(err));

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

function execSQLQuery(sqlQry) {
  return global.conn.request().query(sqlQry);
}

app.post("/matricula", async (req, res) => {
  const ra = parseInt(req.body.ra);
  const cod = parseInt(req.body.cod);
  const nota = parseFloat(req.body.nota);
  const freq = parseFloat(req.body.freq);

  const selectedAluno = await execSQLQuery(
    `SELECT * FROM Alunos WHERE RA = ${ra}`
  );
  if (selectedAluno.recordset.length === 0)
    res.json({ logradouro: "RA inválido!" });

  const selectedDisciplina = await execSQLQuery(
    `SELECT * FROM Disciplinas WHERE COD = ${cod}`
  );
  if (selectedDisciplina.recordset.length === 0)
    res.json({ logradouro: "Disciplina inválida!" });

  const selectedMatricula = await execSQLQuery(
    `SELECT * FROM Matriculas WHERE COD = ${cod} AND RA = ${ra}`
  );
  if (selectedMatricula.recordset.length === 0)
    res.json({ logradouro: "Matricula inválida!" });

  if (nota < 0 || nota > 10) res.json({ logradouro: "Nota inválida!" });

  if (freq < 0 || freq > 1) res.json({ logradouro: "Frequência inválida!" });

  await execSQLQuery(`DELETE Matriculas WHERE RA=${ra} AND COD=${cod}`);

  await execSQLQuery(
    `INSERT INTO RESULTADOS VALUES(${ra}, ${cod}, ${nota}, ${freq})`
  );

  return res.json({ logradouro: "Sucesso na consulta!" });
});

app.listen(port);
console.log("API funcionando!");
