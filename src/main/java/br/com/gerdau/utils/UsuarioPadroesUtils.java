package br.com.gerdau.utils;

import br.com.atomic.framework.utils.Utils;
import br.com.gerdau.enumValues.UsuariosPadroes;

import java.util.ArrayList;
import java.util.List;

import static br.com.gerdau.enumValues.UsuariosPadroes.*;

public class UsuarioPadroesUtils
{

    private static String perfil;
    public static String getPerfil() {
        return perfil;
    }

    public static void setPerfil(String perfil) {
        UsuarioPadroesUtils.perfil = perfil;
    }

    public static UsuariosPadroes getUsuario(String user)
    {
        switch (user.toUpperCase())
        {
            case "RBTCXPSCM":
                return RBTCXPSCM;
            case "RBTCXPEPP":
                return RBTCXPEPP;
            case "RBTCXPPED":
                return RBTCXPPED;
            case "RBTCXPRTC":
                return RBTCXPRTC;
            case "RBTCXPQUA":
                return RBTCXPQUA;
            case "RBTCXPSELLER":
                return RBTCXPSELLER;
            case "RBTCXPGAT":
                return RBTCXPGAT;
            case "RBTCXPCLIENT":
                return RBTCXPCLIENT;
            case "RBTCXPGFMI":
                return RBTCXPGFMI;
            case "RBTCXPCCMICART":
                return RBTCXPCCMICART;
            case "RBTCXPCCMIFIN":
                return RBTCXPCCMIFIN;
            case "RBTCXPCCMICOT":
                return RBTCXPCCMICOT;
            case "RBTCXPCCMI":
                return RBTCXPCCMI;
            case "RBTCXPSALESREP":
                return RBTCXPSALESREP;
            case "RBTCXPCLNTENG":
                return RBTCXPCLNTENG;
            case "RBTCXPCLNTSPA":
                return RBTCXPCLNTSPA;
            case "RBTCXPCCMECART":
                return RBTCXPCCMECART;
            case "RBTCXPCCMEFIN":
                return RBTCXPCCMEFIN;
            case "RBTCXPCCMECOT":
                return RBTCXPCCMECOT;
            case "RBTCXPCCME":
                return RBTCXPCCME;
            default:
                return null;
        }
    }

    public static UsuariosPadroes getUsuarioVendedorAleatorio_ParaCotacao(){
        List<UsuariosPadroes> listaUsuariosVendedores = new ArrayList<UsuariosPadroes>();
//        listaUsuariosVendedores.add(RBTCXPCLIENT); //Senha expirada
//        listaUsuariosVendedores.add(RBTCXPGFMI); //não apresenta frase "escolho cotar o produto'
        listaUsuariosVendedores.add(RBTCXPCCMICOT);
//        listaUsuariosVendedores.add(RBTCXPCCMI); //não apresenta cotação
//        listaUsuariosVendedores.add(RBTCXPSALESREP); ESPANHOL
//        listaUsuariosVendedores.add(RBTCXPCLNTENG); INGLÊS
//        listaUsuariosVendedores.add(RBTCXPCLNTSPA); ESPANHOL
//        listaUsuariosVendedores.add(RBTCXPCCMECOT); INGLÊS
//        listaUsuariosVendedores.add(RBTCXPCCME); INGLÊS

        return listaUsuariosVendedores.get(Utils.randomNumberInRange(0, listaUsuariosVendedores.size()-1));
    }

    public static UsuariosPadroes getUsuarioGerdauAleatorio_ParaCotacao(){
        List<UsuariosPadroes> listaUsuariosVendedores = new ArrayList<UsuariosPadroes>();
        listaUsuariosVendedores.add(RBTCXPRTC);
//        listaUsuariosVendedores.add(RBTCXPSELLER); ESPANHOL

        return listaUsuariosVendedores.get(Utils.randomNumberInRange(0, listaUsuariosVendedores.size()-1));
    }

    public static UsuariosPadroes getUsuarioAdmin(){
        return RBTCXPGAT;
    }

    public static UsuariosPadroes getUsuarioVendedor(){
        return RBTCXPSELLER;
    }
}
