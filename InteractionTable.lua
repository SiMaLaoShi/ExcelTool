local InteractionTable = {
	[711000001] = {
		['ID'] = 711000001,
		['Name'] = '抱拳',
		['Icon'] = 'ico_jiaohu_dan01',
		['Type'] = 1,
		['Action'] = 'Jh_Fuels',
		['ConditionNum1'] = 5,
		['TalkNote1'] = {"久仰久仰！"},
		},
	[711000002] = {
		['ID'] = 711000002,
		['Name'] = '张望',
		['Icon'] = 'ico_jiaohu_dan02',
		['Type'] = 1,
		['Action'] = 'Jh_Looking',
		['TalkNote1'] = {"左看看，右看看"},
		},
	[711000003] = {
		['ID'] = 711000003,
		['Name'] = '作揖',
		['Icon'] = 'ico_jiaohu_dan03',
		['Type'] = 1,
		['Action'] = 'Jh_Fuels',
		['TalkNote1'] = {"你好"},
		},
	[711000004] = {
		['ID'] = 711000004,
		['Name'] = '无奈',
		['Icon'] = 'ico_jiaohu_dan04',
		['Type'] = 1,
		['Action'] = 'Jh_Helpless',
		['TalkNote1'] = {"唉！"},
		},
	[711000005] = {
		['ID'] = 711000005,
		['Name'] = '哭泣',
		['Icon'] = 'ico_jiaohu_dan05',
		['Type'] = 1,
		['Action'] = 'Jh_Cry',
		['TalkNote1'] = {"呜呜呜"},
		},
	[711000006] = {
		['ID'] = 711000006,
		['Name'] = '鼓掌',
		['Icon'] = 'ico_jiaohu_dan06',
		['Type'] = 1,
		['Action'] = 'Jh_Applause',
		['TalkNote1'] = {"啪啪啪"},
		},
	[711000007] = {
		['ID'] = 711000007,
		['Name'] = '跳舞',
		['Icon'] = 'ico_jiaohu_tiaowu07',
		['Type'] = 1,
		['Loop'] = 1,
		['Action'] = 'Jh_Dance',
		['TalkNote1'] = {"舞动起来"},
		},
	[712000001] = {
		['ID'] = 712000001,
		['Name'] = '牵手',
		['Icon'] = 'ico_jiaohu_shuang02',
		['Type'] = 2,
		['Loop'] = 1,
		['Action'] = 'Jh_Hold_1',
		['Action2'] = 'Jh_Hold_2',
		['Action3'] = 'Jh_Hold_3',
		['Action4'] = 'Jh_Hold_4',
		['Move'] = 1,
		['Distance'] = 50.00
		},
	[712000002] = {
		['ID'] = 712000002,
		['Name'] = '拥抱',
		['Icon'] = 'ico_jiaohu_shuang03',
		['Type'] = 2,
		['Loop'] = 1,
		['Action'] = 'Jh_Hug_1',
		['Action2'] = 'Jh_Hug_2',
		['Action3'] = 'Jh_Hug_3',
		['Action4'] = 'Jh_Hug_4',
		['Condition'] = 4,
		['ConditionNum1'] = 100,
		['Distance'] = 20.00
		},
	[712000003] = {
		['ID'] = 712000003,
		['Name'] = '公主抱',
		['Icon'] = 'ico_jiaohu_shuang04',
		['Type'] = 2,
		['Loop'] = 1,
		['Action'] = 'Jh_Held_1',
		['Action2'] = 'Jh_Held_2',
		['Action3'] = 'Jh_Held_3',
		['Action4'] = 'Jh_Held_4',
		['Condition'] = 4,
		['ConditionNum1'] = 300,
		['Distance'] = 20.00
		},
	[712000004] = {
		['ID'] = 712000004,
		['Name'] = '接吻',
		['Icon'] = 'ico_jiaohu_shuang01',
		['Type'] = 2,
		['Loop'] = 1,
		['Action'] = 'Jh_Kiss_1',
		['Action2'] = 'Jh_Kiss_2',
		['Action3'] = 'Jh_Kiss_3',
		['Action4'] = 'Jh_Kiss_4',
		['Condition'] = 4,
		['ConditionNum1'] = 1314,
		['Distance'] = 20.00
		},
	[713000001] = {
		['ID'] = 713000001,
		['Name'] = '挑衅',
		['Type'] = 3,
		['Action'] = '0',
		['TalkNote1'] = {"我%s天下无敌，不服的给我趴着！"},
		['TalkNote2'] = {"%s在紫禁之巅将%s击毙，威震四方！"},
		},
	[713000002] = {
		['ID'] = 713000002,
		['Name'] = '朗诵',
		['Type'] = 3,
		['Action'] = '0',
		['TalkNote1'] = {"%s正在朗诵诗歌：啊！大海你都是水！"},
		['TalkNote2'] = {"%s正在对着%s朗诵诗歌：啊！美女你大长腿！"},
		},
	[713000003] = {
		['ID'] = 713000003,
		['Name'] = '生气',
		['Type'] = 3,
		['Action'] = '0',
		['TalkNote1'] = {"%s在生闷气：好气啊！"},
		['TalkNote2'] = {"%s在对着%s发脾气：mmp！"},
		},
	[713000004] = {
		['ID'] = 713000004,
		['Name'] = '喧哗',
		['Type'] = 3,
		['Action'] = '0',
		['TalkNote1'] = {"%s大声喧哗，像一群鸭子一样"},
		['TalkNote2'] = {"%s和%s在大声喧哗，像一群鸭子一样"},
		},
	[713000005] = {
		['ID'] = 713000005,
		['Name'] = '唱歌',
		['Type'] = 3,
		['Action'] = '0',
		['TalkNote1'] = {"%s在唱歌：我们不一样，不一样……"},
		['TalkNote2'] = {"%s对着%s唱歌：我吃火锅，你吃火锅底料！"},
		},
	[713000006] = {
		['ID'] = 713000006,
		['Name'] = '张望',
		['Type'] = 3,
		['Action'] = '0',
		['TalkNote1'] = {"%s正在偷偷摸摸四处张望"},
		['TalkNote2'] = {"%s正在偷偷摸摸偷看%s，似乎有不轨"},
		},
	[713000007] = {
		['ID'] = 713000007,
		['Name'] = '哭泣',
		['Type'] = 3,
		['Action'] = '0',
		['TalkNote1'] = {"%s蹲在地上哭泣，似乎被人甩了"},
		['TalkNote2'] = {"%s怒斥%s始乱终弃，不禁流下了眼泪"},
		},
	[713000008] = {
		['ID'] = 713000008,
		['Name'] = '害羞',
		['Type'] = 3,
		['Action'] = '0',
		['TalkNote1'] = {"%s自我YY过头，在原地扭扭捏捏"},
		['TalkNote2'] = {"%s偷看了%s一眼，不禁满面红晕"},
		},
	[713000009] = {
		['ID'] = 713000009,
		['Name'] = '逃跑',
		['Type'] = 3,
		['Action'] = '0',
		['TalkNote1'] = {"%s正在准备行李跑路"},
		['TalkNote2'] = {"%s正在准备带%s跑路去泰国重获新生"},
		},
	[713000010] = {
		['ID'] = 713000010,
		['Name'] = '叫卖',
		['Type'] = 3,
		['Action'] = '0',
		['TalkNote1'] = {"%s正在叫卖：不要998，只要388"},
		['TalkNote2'] = {"%s正在向%s推销：吃肾宝，男人不得了，女人受不了"},
		}
	}
local __default_values = {
	['ID'] = 0,	--索引
	['Name'] = "",	--动作名称
	['Icon'] = "",	--美术图标
	['Type'] = "",	--动作类型
	['Loop'] = 0,	--是否循环
	['Action'] = "",	--动作资源
	['Action2'] = "",	--动作资源
	['Action3'] = "",	--动作资源
	['Action4'] = "",	--动作资源
	['Condition'] = 1,	--激活条件
	['ConditionNum1'] = 1,	--参数1
	['TalkNote1'] = {""},	--头顶冒泡文本1
	['TalkNote2'] = {0},	--头顶冒泡文本2
	['Move'] = 0,	--是否可移动
	['Distance'] = 0,	--模型距离
}
do
	local base = {
 		__index = __default_values, --基类，默认值存取
 		__newindex = function()
			error( "Attempt to modify read-only table" )
		end
	}
	for k, v in pairs( InteractionTable ) do
		setmetatable( v, base )
	end
end
return InteractionTable
