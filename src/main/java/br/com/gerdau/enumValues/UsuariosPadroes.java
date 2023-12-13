package br.com.gerdau.enumValues;

import br.com.atomic.framework.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public enum UsuariosPadroes
{
    RBTCXPEPP("rbtcxpepp@gerdau.com.br", "Isa@2022",  "TAE - EPP", "",
                true, true, false, false, true, false),
    RBTCXPPED("rbtcxpped@gerdau.com.br", "Isa@2022", "TAE - P&D", "",
                true, true, false, false, true, false),
    RBTCXPRTC("rbtcxprtc@gerdau.com.br", "Isa@2022", "TAE - RTC", "",
                true, true, false, false, false, true),
    RBTCXPQUA("rbtcxpqua@gerdau.com.br", "Isa@2022", "TAE - GQ", "",
                           true, true, false, false, true, false),
    RBTCXPSELLER("rbtcxpseller@gerdau.com.br", "Isa@2022", "Vendedor", "",
                        true, true, true, true, true, true),
    RBTCXPGAT("rbtcxpgat@gerdau.com.br", "Isa@2022", "Admin", "",
                        true, true, true, true, true, true),
    RBTCXPCLIENT("rbtcxpclient@gerdau.com.br", "Isa@2022", "Gestor Cliente MI", "Açotubo (matriz - 0100242468)",
                    true, true, true, true, true, false),

    //Painel de Gestão deve apresentar somente blocos de finança
    RBTCXPGFMI("rbtcxpgfmi@gerdau.com.br", "Isa@2022", "Gestor Filial MI", "General Chains (0100541796)",
                            true, true, true, true, true, false),

    //Painel de Gestão deve apresentar somente blocos de finança
    RBTCXPCCMICART("rbtcxpccmicart@gerdau.com.br", "Isa@2022", "Cliente Comum MI (Cart)", "Açovisa (0100819474 e 0100244237)",
                        false, true, false, true, true, false),
    RBTCXPCCMIFIN("rbtcxpccmifin@gerdau.com.br", "Isa@2022", "Cliente Comum MI (Fin)", "Dana (0100792177 e 0100243245)",
                            false, false, true, false, true, false),
    RBTCXPCCMICOT("rbtcxpccmicot@gerdau.com.br", "Isa@2022", "Cliente Comum MI (Cot)", "Toyota (0100243320)",
                        true, false, false, false, false, false),
    RBTCXPCCMI("rbtcxpccmi@gerdau.com.br", "Isa@2022", "Cliente Comum MI (Cart + Fin + Cot)", "Weg (0100245552 e 0100508558)",
                            true, true, true, true, true, false),
    RBTCXPSALESREP("rbtcxpsalesrep@gerdau.com.br", "Isa@2022", "Vendedor S26 (repres. de vendas) -ME", "0100058722 - DRILLCO TOOLS; 0100121451 - CORPORACION NACIONAL DEL COBRE DEL CHILE; 0100197999 - ADLER STEEL CHILE SPA; 0100058955 - ACEROS BRAVO LIMITADA; 0100121584 - KUPFER HNOS S A",
                        true, true, true, false, false, false),
    RBTCXPCLNTENG("rbtcxpclienteng@gerdau.com.br", "Isa@2022", "Gestor Cliente ME", "Musashi (matriz - 0100046660)",
                                true, true, true, false, false, false),
    RBTCXPCLNTSPA("rbtcxpclientspa@gerdau.com.br", "Isa@2022", "Gestor Filial ME", "Absteel (0100002446)",
                    true, true, true, false, false, false),
    RBTCXPCCMECART("rbtcxpccmecart@gerdau.com.br", "Isa@2022", "Cliente Comum ME (Cart)", "Rubol (0100046630)",
                        false, true, false, false, false, false),
    RBTCXPCCMEFIN("rbtcxpccmefin@gerdau.com.br", "Isa@2022", "Cliente Comum ME (Fin)", "Fric Rot (0100046646)",
                        false, false, true, false, false, false),
    RBTCXPCCMECOT("rbtcxpccmecot@gerdau.com.br", "Isa@2022", "Cliente Comum ME (Cot)", "Hirschvogel (0100687644)",
                        true, false, false, false, false, false),
    RBTCXPCCME("rbtcxpccme@gerdau.com.br", "Isa@2022", "Cliente Comum ME (Cart + Fin + Cot)", "Magellan (0100827679)",
                    true, true, true, false, false, false),

    RBTCXPSCM("cpg-rbtc@gerdau.com.br", "Isa@2022", "Supply Chain", "",
                          true, true, true, false, false, true);


    public String email;
    public String senha;
    public String perfil;
    public String cliente;
    public boolean isCotacao;
    public boolean isCarteira;
    public boolean isFinanca;
    public boolean isImplantacao;
    public boolean isPainelGestao;
    public boolean isManifestacao;

    UsuariosPadroes(String email, String senha, String perfil, String cliente,
                        boolean isCotacao, boolean isCarteira, boolean isFinanca,
                            boolean isImplantacao, boolean isPainelGestao, boolean isManifestacao)
    {
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.cliente = cliente;
        this.isCotacao = isCotacao;
        this.isCarteira = isCarteira;
        this.isFinanca = isFinanca;
        this.isImplantacao = isImplantacao;
        this.isPainelGestao = isPainelGestao;
        this.isManifestacao = isManifestacao;
    }

    public String getEmail(){
        return email;
    }
    public String getSenha(){
        return senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public String getCliente() {
        return cliente;
    }

    public boolean isCotacao() {
        return isCotacao;
    }

    public boolean isCarteira() {
        return isCarteira;
    }

    public boolean isFinanca() {
        return isFinanca;
    }

    public boolean isImplantacao() {
        return isImplantacao;
    }

    public boolean isPainelGestao() {
        return isPainelGestao;
    }

    public boolean isManifestacao() {
        return isManifestacao;
    }


}
