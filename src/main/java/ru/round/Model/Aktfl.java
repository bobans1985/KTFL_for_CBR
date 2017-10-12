package ru.round.Model;


import java.sql.Timestamp;

/**
 * Created by GrishukovVM on 10.02.2016.
 */
public abstract class Aktfl {
    /*макет*/
    String id_zaemchika;
    String name;
    String inn;
    String rezident;
    String pol;
    Timestamp data_rog;
    String adres_reg;
    String adres;
    String document;
    String nomer_documenta;
    String kem_vidan_document;
    Timestamp data_vidachi_documenta;
    String semeinoe_pol;
    String kol_igdivencev;
    String dolgenost;
    String mesto_rab;
    String inn_rab;
    String doxod;
    String rasxod;
    String plategsposb;
    String max_kredit;
    String scoring_ocenka;
    String id_dogovora;
    String nomer_dogovora;
    Timestamp data_dogovora;
    Timestamp data_vidachi;
    Timestamp data_konca_dogovora;
    Timestamp data_zakr_dogovora;
    String summa_kredita;
    String vid_kredita;
    String vid_kredita_115;
    String kategory_kredita_psk;
    String programma_kredita;
    String cel_kredita;
    String procent_stavka;
    String procent_stavka_na_datu_vidachi;
    String regim_yplat_procent;
    String regim_yplat_osnov;
    String srok_yplat_procent;
    String srok_yplat_osnov;
    //////////////////////////////////////////////////////
    String polnai_stoimost_kredita;
    //////////////////////////////////////////////////////
    String summa_kredit_trebovan;
    String valut_kredit_trebovan;
    String date1;
    String summa_kredit_trebovan_pokursu_;
    String zadolgen_prosrochen_pokursu;
    String nach_procent;
    String prosrochen_procent;
    String prosrochen_procent_vnebal;
    String vikyplen_summa_trebovan_all;
    String vikyplen_summa_trebovan_prosr;
    String discont;
    String komissii;
    String summa_rezerva_po_sroch;
    String reserv_po_prosroch;
    String reserv_prosroch_procent;
    String reserv_nachislen_procent;
    String rezerv_vikyplen_summa_trebovan;
    String ssyd_chet;
    String chet_nachislen_procentov;
    String chet_prosrochen_zadolgen;
    String chet_prosrochen_procentov;
    String chet_prosrochen_procentov_vne;
    String tekuh_schet;
    String schet_po_vikyplen_ssydam;
    String dlina_prosrochen_plateg;
    String pos;
    String overdue_interval;
    String procent_rezerv;
    String kategor_kachestv;
    String vid_obespech;
    String info_obespech;
    String info_straxovan_obespech;
    String stoimost_obespech;
    String vnebalanc_chet_obespech;
    String straxov_sum;
    String cod_podrazdelen_kreditnoijrg;
    String fio_sozaemhika;
    String priznak_restrukt;
    String zarplatnie_cheta;
    String izmenenie_dogovora;
    String tip_platega;
    String razmer_annyitetnogo_platega;
    Timestamp data_posl_pogash_proc;
    Timestamp data_posl_pogash_ssud;
    String soglasie_predostavlenie_bki;
    String soglasie_polychenie_bki;
    String priznak_napravlenie_ssyd;

}
