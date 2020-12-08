delete from proposta;
delete from cliente;

insert into cliente (id, cnpj, razao_social, telefone, email)
values (10001, '17778490000154', 'Exemplo2', '51977885566', 'teste2@teste2.com.br');

insert into cliente (id, cnpj, razao_social, telefone, email)
values (10002, '75130657000172', 'Exemplo3', '51977884433', 'teste3@teste3.com.br');

insert into proposta (id, descricao, valor, data, status, cliente_id)
values(20001, 'Proposta qualquer', 25000, '2020-03-01', 'APROVADA', 10001);
