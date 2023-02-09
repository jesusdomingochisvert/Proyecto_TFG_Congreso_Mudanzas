package com.example.congresotfg.common.utils

object Constants {

    const val CONGRESO_URL = "http://apicei58.ieslasenia.org"

    const val POST_LOGIN_PATH = "/logear"

    const val GET_ASISTENTE_PATH = "/asistente/{id}"
    const val GET_ASISTENTE_VALORA_EVENTO_PATH = "/asistente/{id_asistente}/valora/{id_evento}"

    const val POST_ASISTENTE_VALORA_EVENTO_PATH = "/asistente/{id_asistente}/valora/{id_evento}"

    const val PUT_ASISTENTE_PATH = "/asistente/{id}"

    const val GET_EVENTOS_PATH = "/eventos"
    const val GET_EVENTO_PATH = "/evento/{id}"
    const val GET_VALORACION_PATH = "/evento/{id_evento}/valoraciones"
    const val GET_EVENTO_PONENTES_PATH = "/eventoPonentes/{id_evento}"
    const val GET_EVENTO_ASISTENTES_PATH = "/evento/{id_evento}/asistentes"
    const val GET_ASISTENTE_EVENTOS_PATH = "/asistente/{id_asistente}/eventos"

    const val GET_ACTIVIDADES_PATH = "/actividades"

    const val GET_RESTAURANTE_PATH = "/puestoComida/{id_puesto_comida}"
    const val GET_RESTAURANTES_PATH = "/puestosComida"

    const val GET_ALLPARTNERS_PATH = "/socios"

    const val GET_SOCIO = "/asistente/{id_asistente}/socio"

    const val GET_PATROCINADORES_PATH = "/patrocinadores"

    const val GET_ENTRADA_PATH = "/asistente/{id_asistente}/entrada"

    const val GET_BONOS_ASISTENTE_PATH = "/asistente/{id_asistente}/bonos"
    const val DELETE_BONO_ASISTENTE_PATH = "/asistente/{id_asistente}/bono/{id_bono}"

}