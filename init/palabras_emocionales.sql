-- ========================================
-- Diccionario Emocional Extendido
-- Para clasificación BoW de tickets
-- ========================================

USE mydatabase;

-- Limpiar datos existentes (opcional)
-- DELETE FROM diccionario_emocional;

-- ========================================
-- FRUSTRACIÓN (palabras que indican molestia, enojo, estrés)
-- ========================================
INSERT INTO diccionario_emocional (palabra, tipo, categoria) VALUES
    -- Palabras básicas de frustración
    ('enojado', 'Emocional', 'Frustración'),
    ('enojada', 'Emocional', 'Frustración'),
    ('molesto', 'Emocional', 'Frustración'),
    ('molesta', 'Emocional', 'Frustración'),
    ('estresado', 'Emocional', 'Frustración'),
    ('estresada', 'Emocional', 'Frustración'),
    ('preocupado', 'Emocional', 'Frustración'),
    ('preocupada', 'Emocional', 'Frustración'),
    ('irritado', 'Emocional', 'Frustración'),
    ('irritada', 'Emocional', 'Frustración'),
    ('frustrado', 'Emocional', 'Frustración'),
    ('frustrada', 'Emocional', 'Frustración'),
    ('agobiado', 'Emocional', 'Frustración'),
    ('agobiada', 'Emocional', 'Frustración'),
    ('desanimado', 'Emocional', 'Frustración'),
    ('desanimada', 'Emocional', 'Frustración'),
    ('insatisfecho', 'Emocional', 'Frustración'),
    ('insatisfecha', 'Emocional', 'Frustración'),
    
    -- Expresiones de frustración
    ('molestia', 'Emocional', 'Frustración'),
    ('enojo', 'Emocional', 'Frustración'),
    ('frustración', 'Emocional', 'Frustración'),
    ('estrés', 'Emocional', 'Frustración'),
    ('irritación', 'Emocional', 'Frustración'),
    ('disgusto', 'Emocional', 'Frustración'),
    ('fastidio', 'Emocional', 'Frustración'),
    ('indignación', 'Emocional', 'Frustración'),
    
    -- Adjetivos intensos
    ('furioso', 'Emocional', 'Frustración'),
    ('furiosa', 'Emocional', 'Frustración'),
    ('rabioso', 'Emocional', 'Frustración'),
    ('rabiosa', 'Emocional', 'Frustración'),
    ('desesperado', 'Emocional', 'Frustración'),
    ('desesperada', 'Emocional', 'Frustración'),
    ('cansado', 'Emocional', 'Frustración'),
    ('cansada', 'Emocional', 'Frustración'),
    ('harto', 'Emocional', 'Frustración'),
    ('harta', 'Emocional', 'Frustración'),
    
    -- Verbos y expresiones
    ('no puedo', 'Emocional', 'Frustración'),
    ('no funciona', 'Emocional', 'Frustración'),
    ('no sirve', 'Emocional', 'Frustración'),
    ('no anda', 'Emocional', 'Frustración'),
    ('falla', 'Emocional', 'Frustración'),
    ('error', 'Emocional', 'Frustración'),
    ('problema', 'Emocional', 'Frustración'),
    ('mal', 'Emocional', 'Frustración'),
    ('terrible', 'Emocional', 'Frustración'),
    ('horrible', 'Emocional', 'Frustración'),
    ('pésimo', 'Emocional', 'Frustración'),
    ('fatal', 'Emocional', 'Frustración')
ON DUPLICATE KEY UPDATE palabra=palabra;

-- ========================================
-- URGENCIA (palabras que indican prisa, criticidad)
-- ========================================
INSERT INTO diccionario_emocional (palabra, tipo, categoria) VALUES
    -- Palabras de urgencia directa
    ('urgente', 'Emocional', 'Urgencia'),
    ('urgencia', 'Emocional', 'Urgencia'),
    ('ahora', 'Emocional', 'Urgencia'),
    ('ya', 'Emocional', 'Urgencia'),
    ('rápido', 'Emocional', 'Urgencia'),
    ('rápida', 'Emocional', 'Urgencia'),
    ('rápidamente', 'Emocional', 'Urgencia'),
    ('inmediato', 'Emocional', 'Urgencia'),
    ('inmediata', 'Emocional', 'Urgencia'),
    ('inmediatamente', 'Emocional', 'Urgencia'),
    
    -- Prioridad
    ('prioridad', 'Emocional', 'Urgencia'),
    ('prioritario', 'Emocional', 'Urgencia'),
    ('prioritaria', 'Emocional', 'Urgencia'),
    ('importante', 'Emocional', 'Urgencia'),
    
    -- Tiempo
    ('apresurado', 'Emocional', 'Urgencia'),
    ('apresurada', 'Emocional', 'Urgencia'),
    ('pronto', 'Emocional', 'Urgencia'),
    ('cuanto antes', 'Emocional', 'Urgencia'),
    ('lo antes posible', 'Emocional', 'Urgencia'),
    ('asap', 'Emocional', 'Urgencia'),
    ('hoy', 'Emocional', 'Urgencia'),
    
    -- Criticidad
    ('imperativo', 'Emocional', 'Urgencia'),
    ('imperativa', 'Emocional', 'Urgencia'),
    ('crítico', 'Emocional', 'Urgencia'),
    ('crítica', 'Emocional', 'Urgencia'),
    ('inminente', 'Emocional', 'Urgencia'),
    ('grave', 'Emocional', 'Urgencia'),
    ('emergencia', 'Emocional', 'Urgencia'),
    ('emergente', 'Emocional', 'Urgencia'),
    
    -- Expresiones de tiempo limitado
    ('necesito', 'Emocional', 'Urgencia'),
    ('requiero', 'Emocional', 'Urgencia'),
    ('necesito ayuda', 'Emocional', 'Urgencia'),
    ('ayuda', 'Emocional', 'Urgencia'),
    ('auxilio', 'Emocional', 'Urgencia'),
    ('sos', 'Emocional', 'Urgencia'),
    
    -- Intensificadores
    ('muy urgente', 'Emocional', 'Urgencia'),
    ('super urgente', 'Emocional', 'Urgencia'),
    ('extremadamente urgente', 'Emocional', 'Urgencia'),
    ('vital', 'Emocional', 'Urgencia'),
    ('crucial', 'Emocional', 'Urgencia'),
    ('esencial', 'Emocional', 'Urgencia')
ON DUPLICATE KEY UPDATE palabra=palabra;

-- ========================================
-- NEUTRALIDAD (palabras que indican calma, normalidad)
-- ========================================
INSERT INTO diccionario_emocional (palabra, tipo, categoria) VALUES
    -- Palabras neutrales básicas
    ('neutral', 'Emocional', 'Neutralidad'),
    ('tranquilo', 'Emocional', 'Neutralidad'),
    ('tranquila', 'Emocional', 'Neutralidad'),
    ('moderado', 'Emocional', 'Neutralidad'),
    ('moderada', 'Emocional', 'Neutralidad'),
    ('equilibrado', 'Emocional', 'Neutralidad'),
    ('equilibrada', 'Emocional', 'Neutralidad'),
    ('estable', 'Emocional', 'Neutralidad'),
    ('normal', 'Emocional', 'Neutralidad'),
    ('normalidad', 'Emocional', 'Neutralidad'),
    
    -- Estado de ánimo neutral
    ('indiferente', 'Emocional', 'Neutralidad'),
    ('regular', 'Emocional', 'Neutralidad'),
    ('medio', 'Emocional', 'Neutralidad'),
    ('media', 'Emocional', 'Neutralidad'),
    ('calmado', 'Emocional', 'Neutralidad'),
    ('calmada', 'Emocional', 'Neutralidad'),
    ('sereno', 'Emocional', 'Neutralidad'),
    ('serena', 'Emocional', 'Neutralidad'),
    
    -- Consultas normales
    ('consulta', 'Emocional', 'Neutralidad'),
    ('pregunta', 'Emocional', 'Neutralidad'),
    ('información', 'Emocional', 'Neutralidad'),
    ('informar', 'Emocional', 'Neutralidad'),
    ('revisar', 'Emocional', 'Neutralidad'),
    ('verificar', 'Emocional', 'Neutralidad'),
    ('confirmar', 'Emocional', 'Neutralidad'),
    
    -- Palabras técnicas neutras
    ('solicito', 'Emocional', 'Neutralidad'),
    ('solicitud', 'Emocional', 'Neutralidad'),
    ('requiero', 'Emocional', 'Neutralidad'),
    ('requerimiento', 'Emocional', 'Neutralidad'),
    ('necesito', 'Emocional', 'Neutralidad'),
    
    -- Cortesía neutral
    ('por favor', 'Emocional', 'Neutralidad'),
    ('favor', 'Emocional', 'Neutralidad'),
    ('podría', 'Emocional', 'Neutralidad'),
    ('podrías', 'Emocional', 'Neutralidad'),
    ('quisiera', 'Emocional', 'Neutralidad'),
    
    -- Estados normales
    ('bien', 'Emocional', 'Neutralidad'),
    ('ok', 'Emocional', 'Neutralidad'),
    ('está bien', 'Emocional', 'Neutralidad'),
    ('funciona', 'Emocional', 'Neutralidad')
ON DUPLICATE KEY UPDATE palabra=palabra;

-- ========================================
-- POSITIVO (palabras que indican satisfacción, agradecimiento)
-- ========================================
INSERT INTO diccionario_emocional (palabra, tipo, categoria) VALUES
    -- Emociones positivas básicas
    ('feliz', 'Emocional', 'Positivo'),
    ('contento', 'Emocional', 'Positivo'),
    ('contenta', 'Emocional', 'Positivo'),
    ('motivado', 'Emocional', 'Positivo'),
    ('motivada', 'Emocional', 'Positivo'),
    ('entusiasmado', 'Emocional', 'Positivo'),
    ('entusiasmada', 'Emocional', 'Positivo'),
    ('satisfecho', 'Emocional', 'Positivo'),
    ('satisfecha', 'Emocional', 'Positivo'),
    ('alegre', 'Emocional', 'Positivo'),
    
    -- Estados positivos
    ('animado', 'Emocional', 'Positivo'),
    ('animada', 'Emocional', 'Positivo'),
    ('optimista', 'Emocional', 'Positivo'),
    ('agradable', 'Emocional', 'Positivo'),
    ('positivo', 'Emocional', 'Positivo'),
    ('positiva', 'Emocional', 'Positivo'),
    
    -- Agradecimiento
    ('gracias', 'Emocional', 'Positivo'),
    ('agradezco', 'Emocional', 'Positivo'),
    ('agradecido', 'Emocional', 'Positivo'),
    ('agradecida', 'Emocional', 'Positivo'),
    ('agradecimiento', 'Emocional', 'Positivo'),
    ('muchas gracias', 'Emocional', 'Positivo'),
    ('mil gracias', 'Emocional', 'Positivo'),
    ('muy agradecido', 'Emocional', 'Positivo'),
    
    -- Aprobación
    ('excelente', 'Emocional', 'Positivo'),
    ('perfecto', 'Emocional', 'Positivo'),
    ('perfecta', 'Emocional', 'Positivo'),
    ('genial', 'Emocional', 'Positivo'),
    ('estupendo', 'Emocional', 'Positivo'),
    ('estupenda', 'Emocional', 'Positivo'),
    ('maravilloso', 'Emocional', 'Positivo'),
    ('maravillosa', 'Emocional', 'Positivo'),
    ('fantástico', 'Emocional', 'Positivo'),
    ('fantástica', 'Emocional', 'Positivo'),
    
    -- Satisfacción con servicio
    ('satisfacción', 'Emocional', 'Positivo'),
    ('buen servicio', 'Emocional', 'Positivo'),
    ('buena atención', 'Emocional', 'Positivo'),
    ('bien atendido', 'Emocional', 'Positivo'),
    ('resuelto', 'Emocional', 'Positivo'),
    ('solucionado', 'Emocional', 'Positivo'),
    ('funciona bien', 'Emocional', 'Positivo'),
    ('funciona perfecto', 'Emocional', 'Positivo'),
    
    -- Expresiones positivas
    ('me gusta', 'Emocional', 'Positivo'),
    ('me encanta', 'Emocional', 'Positivo'),
    ('muy bien', 'Emocional', 'Positivo'),
    ('super bien', 'Emocional', 'Positivo'),
    ('bueno', 'Emocional', 'Positivo'),
    ('buena', 'Emocional', 'Positivo'),
    ('mejor', 'Emocional', 'Positivo'),
    ('éxito', 'Emocional', 'Positivo'),
    ('exitoso', 'Emocional', 'Positivo'),
    ('exitosa', 'Emocional', 'Positivo')
ON DUPLICATE KEY UPDATE palabra=palabra;

-- ========================================
-- Verificar datos insertados
-- ========================================
SELECT 'Palabras por Categoría Emocional:' as Info;
SELECT categoria, COUNT(*) as total_palabras 
FROM diccionario_emocional 
GROUP BY categoria 
ORDER BY categoria;

SELECT '' as Separador;
SELECT 'Total de palabras emocionales:' as Info, COUNT(*) as Total 
FROM diccionario_emocional;
