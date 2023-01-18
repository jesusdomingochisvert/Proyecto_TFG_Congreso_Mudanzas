package com.example.congresotfg.partnersFragmentModule

import com.example.congresotfg.common.entities.EmpresaEntity
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.entities.SocioEntity

interface SocioListener {

    fun onClickSocio(socioEntity: SocioEntity)

    fun onClickEmpresa(empresaEntity: EmpresaEntity)

}